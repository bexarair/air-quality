package com.bexarair.demo.repositories;

import com.bexarair.demo.models.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {

DBFile findById(String id);

}