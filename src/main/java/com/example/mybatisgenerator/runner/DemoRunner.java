package com.example.mybatisgenerator.runner;

import java.time.LocalDateTime;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.mybatisgenerator.mapper.generated.SampleTableMapper;
import com.example.mybatisgenerator.model.generated.SampleTable;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DemoRunner implements ApplicationRunner{

	private final SampleTableMapper sampleTableMapper;

	@Transactional
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//
		LocalDateTime now = LocalDateTime.now();

		SampleTable sampleTable = new SampleTable();
		sampleTable.setName("testname");
		sampleTable.setAge(20);
		sampleTable.setDeleteFlag(false);
		sampleTable.setCreatedAt(now);
		sampleTable.setUpdatedAt(now);

		sampleTableMapper.insert(sampleTable);

	}

}
