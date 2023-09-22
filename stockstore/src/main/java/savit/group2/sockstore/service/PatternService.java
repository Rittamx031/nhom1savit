package savit.group2.sockstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import savit.group2.sockstore.model.reponse.PatternResponse;
import savit.group2.sockstore.repository.PatternRepository;
import savit.group2.sockstore.service.interfaceservice.PanigationInterface;

@Service
public class PatternService implements PanigationInterface<PatternResponse> {
  @Autowired
  PatternRepository repository;

  @Override
  public List<PatternResponse> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    // TODO Auto-generated method stub
    Sort sort;
    if (sortDir) {
      sort = Sort.by(sortBy).ascending();
    } else {
      sort = Sort.by(sortBy).descending();
    }
    Pageable page = PageRequest.of(pageNo, pageSize, sort);
    return repository.getResposePage(page).getContent();
  }

  @Override
  public int getPageNumber(int rowcount) {
    Pageable pageable = PageRequest.of(1, rowcount);
    Page<PatternResponse> page = repository.getResposePage(pageable);
    int totalPage = page.getTotalPages();
    return totalPage;
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno) {
    Pageable pageable = PageRequest.of(1, rowcount);
    Page<PatternResponse> page = repository.getResposePage(pageable);
    int totalPage = page.getTotalPages();
    int[] rs;
    if (totalPage <= 3) {
      rs = new int[totalPage];
      for (int i = 1; i <= totalPage; i++) {
        rs[i] = i;
      }
      return rs;
    } else {
      rs = new int[3];
      if (pageno <= 2) {
        int[] rs1 = { 1, 2, 3 };
        rs = rs1;
      }
      if (pageno > 2) {
        if (pageno < totalPage - 1) {
          int[] rs1 = { pageno - 1, pageno, pageno + 1 };
          rs = rs1;
        }
        if (pageno >= totalPage - 1) {
          int[] rs1 = { totalPage - 2, totalPage - 1, totalPage };
          rs = rs1;
        }
      }
      return rs;
    }
  }

}
