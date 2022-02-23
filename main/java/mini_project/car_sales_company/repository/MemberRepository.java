package mini_project.car_sales_company.repository;

import lombok.RequiredArgsConstructor;
import mini_project.car_sales_company.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@RequiredArgsConstructor를 통해 자동으로 엔티티 매니저를 주입 받을 수 있다.
    private final EntityManager em;

    //Member를 영속성 컨택스트에 저장한다.
    public void save(Member member) {
        em.persist(member);
    }

    //Member를 DB에서 찾아서 영속성 컨택스트에 담고 그 member를 반환한다.
    public Member findOne(Long id){
        Member member = em.find(Member.class, id);
        return member;
    }

    public List<Member> findAll(){
        List<Member> select_m_from_member_m = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return select_m_from_member_m;
    }

    public List<Member> findByEmail(String email) {
        List<Member> email1 = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
        return email1;
    }
}
