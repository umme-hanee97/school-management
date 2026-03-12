package com.example.schoolmanagement.service;

import java.util.List;

public interface AttachmentService {

        List<String> saveAttachments(List<String> fileB64List, List<String> fileNameList);

        String saveAttachment(String fileB64, String fileName);

        void deleteAttachment(Long id);
}
