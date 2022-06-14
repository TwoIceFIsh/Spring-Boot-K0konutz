package com.kokonut.Service;


import com.kokonut.DTO.mailing.mailingDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service
@Slf4j
public class mailingService {

    public int mail_insert(mailingDTO mailingdto) {

        try {

            // 파일 생성
            File file = new File("./mail_list.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            // 파일 읽기
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            // 파일 읽기(이메일 정보)
            ArrayList<String> mail_list = new ArrayList<>();

            String email = "";
            while ((email = reader.readLine()) != null) {
                mail_list.add(email);

            }
            fr.close();
            reader.close();

            FileWriter fw = new FileWriter(file, false);
            BufferedWriter writer = new BufferedWriter(fw);

            // 파일에 이메일 미존재 시 쓰기 및 저장
            log.info("Before" + String.valueOf(mail_list));
            if (!mail_list.contains(mailingdto.getEmail_address())) {
                log.info("Add Before" + String.valueOf(mail_list));
                mail_list.add(mailingdto.getEmail_address());
                for (String data : mail_list)
                    writer.write(data + "\n");
                writer.close();
                fw.close();
                log.info("After Before" + String.valueOf(mail_list));
                return 1;
            }
            // 파일에 이메일 존재 시 삭제 및 저장
            else {
                log.info("Remove Before" + String.valueOf(mail_list));
                mail_list.remove(mailingdto.getEmail_address());
                for (String data : mail_list) {
                    log.info(data);
                    writer.write(data + "\n");
                }
                writer.close();
                fw.close();
                log.info("Remove After" + String.valueOf(mail_list));
                return 0;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int get_num() {

        try {
            File file = new File("./mail_list.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            if (!file.exists()) {
                file.createNewFile();
            }

            // 파일 읽기(이메일 정보)
            ArrayList<String> mail_list = new ArrayList<>();

            String email = "";
            while ((email = reader.readLine()) != null) {
                mail_list.add(email);
            }
            reader.close();
            fr.close();
            return mail_list.size();

        } catch (Exception e) {

            return 0;
        }
    }
}

