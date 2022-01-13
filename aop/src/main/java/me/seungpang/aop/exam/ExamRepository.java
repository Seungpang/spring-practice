package me.seungpang.aop.exam;

import org.springframework.stereotype.Repository;

import me.seungpang.aop.exam.annotation.Retry;
import me.seungpang.aop.exam.annotation.Trace;

@Repository
public class ExamRepository {

	private static int seq = 0;

	/**
	 * 5번에 1번 실패하는 요청
	 */
	@Trace
	@Retry(value = 4)
	public String save(String itemId) {
		seq++;
		if (seq % 5 == 0) {
			throw new IllegalArgumentException("예외 발생");
		}
		return "ok";
	}
}
