package com.example.demo.Service;


import com.example.demo.DTO.mailing.mailingDTO;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;

@Service
public class mailingService {

    public int mail_insert(mailingDTO mailingdto) {


        try {

            // 파일 생성
            File file = new File("./mail_list.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            // 파일 읽기
            else {

                // 파일 읽기
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);

                // 파일 읽기(이메일 정보)
                ArrayList mail_list = new ArrayList();

                String email = "";
                while ((email = reader.readLine()) != null) {
                    mail_list.add(email);
                }

                // 파일에 이메일 미존재 시 쓰기 및 저장
                if (!mail_list.contains(mailingdto.getEmail_address())) {
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter writer = new BufferedWriter(fw);

                    writer.write(mailingdto.getEmail_address());
                    return 0;


                }
                // 파일에 이메일 존재 시 삭제 및 저장
                else {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
