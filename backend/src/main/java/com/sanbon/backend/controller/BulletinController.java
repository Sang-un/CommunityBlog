package com.sanbon.backend.controller;

import com.sanbon.backend.model.dto.response.post.BulletinResponseDto;
import com.sanbon.backend.model.post.Bulletin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bulletin")
public class BulletinController extends AbsBulletinController<BulletinResponseDto, Bulletin> {
}
