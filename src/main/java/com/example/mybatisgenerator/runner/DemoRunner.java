package com.example.mybatisgenerator.runner;

import java.time.LocalDateTime;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.example.mybatisgenerator.mapper.generated.SampleTableMapper;
import com.example.mybatisgenerator.model.generated.SampleTable;
import com.example.mybatisgenerator.model.generated.SampleTableExample;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class DemoRunner implements ApplicationRunner{

	private final SampleTableMapper sampleTableMapper;

	@Transactional
	@Override
	public void run(ApplicationArguments args) throws Exception {
		//
		LocalDateTime now = LocalDateTime.now();

		// delete
		SampleTableExample deleteExample = new SampleTableExample();
		deleteExample.createCriteria().andIdLessThan(100000000);
		sampleTableMapper.deleteByExample(deleteExample);

		// insert
		SampleTable insertSampleTable = new SampleTable();
		insertSampleTable.setId(1);
		insertSampleTable.setName("testname");
		insertSampleTable.setAge(20);
		insertSampleTable.setDeleteFlag(false);
		insertSampleTable.setCreatedAt(now);
		insertSampleTable.setUpdatedAt(now);

		sampleTableMapper.insert(insertSampleTable);

		// insertSelective
		SampleTable insertSelectiveSampleTable = new SampleTable();
		insertSelectiveSampleTable.setName("insertSelectiveTest");
		try {
			sampleTableMapper.insertSelective(insertSelectiveSampleTable);
		} catch (Exception e) {
			log.error("DBのNOT NULL制約に引っかかっています。");
		}

		// selectByExampleWithBLOBs
		SampleTableExample selectByExampleWithBLOBsExample = new SampleTableExample();
		selectByExampleWithBLOBsExample.createCriteria().andIdEqualTo(1);
		sampleTableMapper.selectByExampleWithBLOBs(selectByExampleWithBLOBsExample).forEach(data -> System.out.println(data.getName()));

		SampleTable updateByExampleSelectiveSampleTable = new SampleTable();
		updateByExampleSelectiveSampleTable.setName("updateByExampleSelectiveTest");

		SampleTableExample updateByExampleSelectiveExample = new SampleTableExample();
		updateByExampleSelectiveExample.createCriteria().andIdEqualTo(1);

		sampleTableMapper.updateByExampleSelective(updateByExampleSelectiveSampleTable, updateByExampleSelectiveExample);

		// countByExample
		SampleTableExample countByExampleExample = new SampleTableExample();
		countByExampleExample.createCriteria();

		long countByExample = sampleTableMapper.countByExample(countByExampleExample);
		log.info("{}件データがありました。", countByExample);
	}


}
