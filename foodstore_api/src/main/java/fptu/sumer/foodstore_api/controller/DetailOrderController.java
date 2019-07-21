package fptu.sumer.foodstore_api.controller;

import fptu.sumer.foodstore_api.entity.DetailOrderEntity;
import fptu.sumer.foodstore_api.reponsitory.DetailOrderRepository;
import fptu.sumer.foodstore_api.reponsitory.ProductRepository;
import fptu.sumer.foodstore_api.responsemodel.DetailOrderResponeModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@Api(value = "Detail Order Management")
public class DetailOrderController {
    @Autowired
    DetailOrderRepository detailOrderRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("detailorder/{id}")
    public ResponseEntity getDetailOrderByOrderId(@PathVariable(value = "id") int id) {
        List<DetailOrderResponeModel> detailOrderResponeModelList = new ArrayList<>();
        List<DetailOrderEntity> listDetailOrder = detailOrderRepository.findAllByOrderIdOrderByOrderIdDesc(id);

        if (listDetailOrder != null) {

            for (DetailOrderEntity ldo : listDetailOrder) {
                String productId = ldo.getProductId();
                String productName = productRepository.findByProId(productId).getProName();
                DetailOrderResponeModel db = new DetailOrderResponeModel(ldo.getId(), ldo.getOrderId(), productId, productName, ldo.getQuantity(), ldo.getPrice());
                if (detailOrderResponeModelList == null) {
                    detailOrderResponeModelList = new ArrayList<>();
                }
                detailOrderResponeModelList.add(db);
            }


            return new ResponseEntity(detailOrderResponeModelList, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
