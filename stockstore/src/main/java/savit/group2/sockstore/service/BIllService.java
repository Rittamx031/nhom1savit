package savit.group2.sockstore.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> findAllBills() {
        return billRepository.findAll();
    }

    public Bill findBillById(UUID id) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        return optionalBill.orElse(null);
    }

    public Bill createBill(Bill bill) {
        // Kiểm tra xem bill có ID hay chưa
        if (bill.getId() != null) {
            throw new IllegalArgumentException("Bill ID should be null for a new bill.");
        }

        // Thực hiện các kiểm tra khác hoặc xử lý trước khi lưu bill

        return billRepository.save(bill);
    }

    public Bill updateBill(UUID id, Bill updatedBill) {
        // Kiểm tra xem bill có tồn tại trong database hay không
        Optional<Bill> optionalExistingBill = billRepository.findById(id);
        if (optionalExistingBill.isEmpty()) {
            throw new IllegalArgumentException("Bill not found with ID: " + id);
        }

        // Thực hiện các kiểm tra khác hoặc xử lý trước khi cập nhật bill
        Bill existingBill = optionalExistingBill.get();

        // Cập nhật các trường của bill với giá trị từ updatedBill
        existingBill.setCustomerName(updatedBill.getCustomerName());
        existingBill.setTotalAmount(updatedBill.getTotalAmount());
        // Cập nhật thêm các trường khác nếu có

        return billRepository.save(existingBill);
    }

    public void deleteBill(UUID id) {
        // Kiểm tra xem bill có tồn tại trong database hay không
        Optional<Bill> optionalExistingBill = billRepository.findById(id);
        if (optionalExistingBill.isEmpty()) {
            throw new IllegalArgumentException("Bill not found with ID: " + id);
        }

        // Xóa bill
        billRepository.deleteById(id);
    }
}

