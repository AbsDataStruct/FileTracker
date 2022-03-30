package org.myfriendlyworld.FileTracker;

import org.myfriendlyworld.FileTracker.dto.PathDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mft")
public class FileTrackerController {

	@GetMapping
	public String welcome() {
		return "welcome ok";
	}
/*
	@GetMapping(value="{p}")//, produces = "application/json")
	public String welcome(@PathVariable String path) {
		return path+"id ok";
	}
*/
	@GetMapping(value="/{path}", produces = "application/json")
	@ResponseBody
	public PathDto welcom(@PathVariable String path) {
		PathDto p=new PathDto(0,path);
		return p;//ResponseEntity.ok(p);
	}
}
