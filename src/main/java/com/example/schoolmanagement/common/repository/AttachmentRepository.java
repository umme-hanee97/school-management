package com.example.schoolmanagement.common.repository;

import com.example.schoolmanagement.common.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
