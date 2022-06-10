package com.shoeshop.repository;

import com.shoeshop.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag getFirstByName(String name);
}
