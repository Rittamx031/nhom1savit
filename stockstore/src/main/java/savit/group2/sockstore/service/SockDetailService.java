package savit.group2.sockstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import savit.group2.sockstore.model.entity.Sock;
import savit.group2.sockstore.service.interfaceservice.PanigationInterface;

/**
 * SockDetailService
 */
@Service
public class SockDetailService implements PanigationInterface<Sock>{

  @Override
  public List<Sock> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPageNo'");
  }

  @Override
  public int getPageNumber(int rowcount) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPageNumber'");
  }

  @Override
  public int[] getPanigation(int rowcount, int pageno) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getPanigation'");
  }

  
}