package savit.group2.sockstore.service;

import java.util.List;

import savit.group2.sockstore.model.request.EmployeeRequset;
import savit.group2.sockstore.service.interfaceservice.PanigationInterface;

public class EmployeeSevice implements PanigationInterface<EmployeeRequset> {

  @Override
  public List<EmployeeRequset> getPageNo(int pageNo, int pageSize, String sortBy, boolean sortDir) {
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
