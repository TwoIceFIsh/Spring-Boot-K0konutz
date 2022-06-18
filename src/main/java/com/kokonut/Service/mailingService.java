package com.kokonut.Service;

import com.kokonut.DTO.mailing.mailingDTO;
import com.kokonut.Entity.articleEntity;
import com.kokonut.Repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class mailingService {


    @Autowired
    ArticleRepository articleRepository;

    public List<articleEntity> getArticleList() {
        return (List<articleEntity>) articleRepository.findAll();
    }

    public Collection<String> get_mail_list() {
        return get_list("./mail_list.txt");
    }

    public int mail_insert(mailingDTO mailingdto) {

        try {

            ArrayList<String> mail_list = get_list("./mail_list.txt");
            File file = create_file("./mail_list.txt");

            FileWriter fw = new FileWriter(file, false);
            BufferedWriter writer = new BufferedWriter(fw);

            // 파일에 이메일 미존재 시 쓰기 및 저장
            if (!mail_list.contains(mailingdto.getEmail_address())) {
                mail_list.add(mailingdto.getEmail_address());
                for (String data : mail_list)
                    writer.write(data + "\n");
                writer.close();
                fw.close();
                return 1;
            }
            // 파일에 이메일 존재 시 삭제 및 저장
            else {
                mail_list.remove(mailingdto.getEmail_address());
                for (String data : mail_list) {
                    writer.write(data + "\n");
                }
                writer.close();
                fw.close();
                return 0;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ArrayList<String> get_list(String file_name) {

        try {
            File file = create_file(file_name);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            // 파일 읽기(이메일 정보)
            ArrayList<String> mail_list = new ArrayList<>();

            String email = "";
            while ((email = reader.readLine()) != null) {
                mail_list.add(email);
            }
            reader.close();
            fr.close();
            return mail_list;

        } catch (Exception ignored) {

        }
        return null;
    }

    public File create_file(String file_name) throws IOException {
        File file = new File(file_name);
        if (!file.exists()) {
            file.createNewFile();
            return file;
        }
        return file;
    }
}

