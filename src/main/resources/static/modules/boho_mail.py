import smtplib
import time
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.utils import formataddr
import datetime

import psycopg2
import requests
from bs4 import BeautifulSoup


def sendMail(output,  email):
    print(
        "############################################## 메일 보내기 ##########################################################")

    ################ 이메일 카운트 증가 ##################

    ###############################################################
    from_addr = formataddr(('SOCH', 'bh.lee@email.com'))

    # 받는사람
    to_addr = formataddr(('보안담당자', email))

    session = None
    try:
        # SMTP 세션 생성
        session = smtplib.SMTP('smtp.gmail.com', 587)
        session.set_debuglevel(True)

        # SMTP 계정 인증 설정
        session.ehlo()
        session.starttls()
        session.login('igloosoil@gmail.com', 'lougwydyuijffjcd')

        # 메일 콘텐츠 설정
        message = MIMEMultipart("mixed")

        # 메일 송/수신 옵션 설정
        message.set_charset('utf-8')
        message['From'] = from_addr
        message['To'] = to_addr
        message['Subject'] = "[보안관제] 보호나라 새로운 게시물알림"

        # 메일 콘텐츠 - 내용
        body = output + "<br><br><br>문의 : Aiden Lee(이병호)<br>솔루션 주소 : https://www.kokonut.today/mail "

        bodyPart = MIMEText(body, 'html', 'utf-8')
        message.attach(bodyPart)

        # 메일 콘텐츠 - 첨부파일

        # 메일 발송
        session.sendmail(from_addr, to_addr, message.as_string())


    except Exception as e:
        print(e)
    finally:
        if session is not None:
            session.quit()

    #################################################################################################################


# 15분마다 크롤링

while (True):
    now = datetime.datetime.now()
    print('수집중 : ' + str(now))
    url = 'https://www.boho.or.kr/data/secNoticeList.do'

    response = requests.get(url)
    if response.status_code == 200:
        html = response.text.strip()
        soup = BeautifulSoup(html, 'html.parser')

        b_id = soup.select('#contentDiv > table > tbody > tr.first > td.colTit > a')[0]['href'].split('?')[1].replace(
            'bulletin_writing_sequence=', '')
        b_no = soup.select('#contentDiv > table > tbody > tr.first > td:nth-child(1)')[0].text.strip()
        b_title = soup.select('#contentDiv > table > tbody > tr.first > td.colTit > a')[0].text.strip()
        b_date = soup.select('#contentDiv > table > tbody > tr.first > td:nth-child(5)')[0].text.strip()
        b_url = 'https://www.boho.or.kr/' + soup.select('#contentDiv > table > tbody > tr.first > td.colTit > a')[0][
            'href']
        output = ''
        db = psycopg2.connect(host='localhost', dbname='postgres', user='postgres', password='Dlqudgh1!', port=5432)
        cursor = db.cursor()

        print(b_id + b_no)

        try:
            cursor.execute("INSERT INTO b_table (b_id,b_no,b_title,b_date,b_url) VALUES (%s,%s,%s,%s,%s)",
                           (b_id, b_no, b_title, b_date, b_url))
            print(b_id + ' ' + b_no + ' ' + b_title + ' ' + b_date + ' ' + b_url)
            output = output + b_id + ' ' + b_no + ' ' + b_title + ' ' + b_date + ' ' + b_url + "<br>"
        except psycopg2.errors.UniqueViolation:
            print()

        cursor.close()
        db.commit()
        db.close()

        if '[공지]' in b_no:
            d_no = 404

        for i in range(2, 10):

            print('next Logic' + str(i))

            b_id = \
            soup.select('#contentDiv > table > tbody > tr:nth-child(' + str(i) + ') > td.colTit > a')[0]['href'].split(
                '?')[1].replace(
                'bulletin_writing_sequence=', '')
            b_no = soup.select('#contentDiv > table > tbody > tr:nth-child(' + str(i) + ') > td:nth-child(1)')[
                0].text.strip()
            b_title = soup.select('#contentDiv > table > tbody > tr:nth-child(' + str(i) + ') > td.colTit > a')[
                0].text.strip()
            b_date = soup.select('#contentDiv > table > tbody > tr:nth-child(' + str(i) + ') > td:nth-child(5)')[
                0].text.strip()
            b_url = 'https://www.boho.or.kr/' + \
                    soup.select('#contentDiv > table > tbody > tr:nth-child(' + str(i) + ') > td.colTit > a')[0]['href']

            db = psycopg2.connect(host='localhost', dbname='postgres', user='postgres', password='Dlqudgh1!', port=5432)
            cursor = db.cursor()

            print(b_id + b_no)
            try:
                cursor.execute("INSERT INTO b_table (b_id,b_no,b_title,b_date,b_url) VALUES (%s,%s,%s,%s,%s)",
                               (b_id, b_no, b_title, b_date, b_url))
                print(b_id + ' ' + b_no + ' ' + b_title + ' ' + b_date + ' ' + b_url)
                output = output + b_id + ' ' + b_no + ' ' + b_title + ' ' + b_date + ' ' + b_url + "<br>"
            except psycopg2.errors.UniqueViolation:
                print()

            cursor.close()
            db.commit()
            db.close()
        
        if output != '':
            f = open('./mail_list.txt', 'r')
            for email_address in f.readline():
                sendMail(output,  email_address)

    print('end')
    time.sleep(900)
