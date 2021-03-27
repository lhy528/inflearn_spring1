package hello.hellospring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	
	// 테스트는 독립적으로 실행해야되기 때문에 beforeEach 넣어준다
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void 회원가입() {
		//given(뭔가가 주어졌을 떄)
		Member member = new Member();
		member.setName("spring");
		
		//when(이걸 하면)
		Long saveId = memberService.Join(member);
		
		
		//then(이게 나와야돼)
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
		
	}
	
	@Test
	public void 중복_회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		//when
		memberService.Join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, ()->memberService.Join(member2));
		
		Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		try {
//			memberService.Join(member2);
//			fail("예외가 발생해야 합니다.");
//		} catch (Exception e) {
//			Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
		
		
		//then
		
	}

	@Test
	void testFindMembers() {
		fail("Not yet implemented");
	}

	@Test
	void testFindOne() {
		fail("Not yet implemented");
	}

}
