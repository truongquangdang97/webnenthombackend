//package com.t2008m.orderdemo.seeder;
//
//import com.github.javafaker.Faker;
//import com.t2008m.orderdemo.entity.*;
//import com.t2008m.orderdemo.entity.enums.OrderSimpleStatus;
//import com.t2008m.orderdemo.entity.enums.ProductSimpleStatus;
//import com.t2008m.orderdemo.repository.OrderRepository;
//import com.t2008m.orderdemo.repository.ProductRepository;
//import com.t2008m.orderdemo.repository.UserRepository;
//import com.t2008m.orderdemo.util.StringHelper;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.*;
//
//import static java.time.LocalDateTime.*;
//import static java.time.LocalDateTime.parse;
//
//@Component
//public class ApplicationSeeder implements CommandLineRunner {
//
//    boolean createSeedData = true;
//    final OrderRepository orderRepository;
//    final ProductRepository productRepository;
//    final UserRepository userRepository;
//    Faker faker;
//    int numberOfProduct = 100;
//    int numberOfOrder = 100;
//    int numberUser = 100;
//
//    public ApplicationSeeder(
//            OrderRepository orderRepository,
//            ProductRepository productRepository,
//            UserRepository userRepository) {
//        this.orderRepository = orderRepository;
//        this.productRepository = productRepository;
//        this.userRepository = userRepository;
//        this.faker = new Faker();
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        if(createSeedData){
//            // Reset dữ liệu
//            orderRepository.deleteAll();
//            productRepository.deleteAll();
//            seedProduct();
//            seedUser();
//            seedOrder();
//        }
//    }
//    public void seedUser(){
//        List<User> users = new ArrayList<>();
//        for (int z =0;z<numberUser;z++){
//            User user = new User();
//            user.setUserId(UUID.randomUUID().toString());
//            user.setNameUser(faker.name().title());
//            user.setPhone(faker.phoneNumber().phoneNumber());
//            users.add(user);
//        }
//        userRepository.saveAll(users);
//    }
//    private void seedProduct() {
//        List<Product> listProduct = new ArrayList<>();
//        for (int i = 0; i < numberOfProduct; i++) {
//            System.out.println(i + 1);
//            Product product = new Product();
//            product.setId(UUID.randomUUID().toString());
//            product.setName(faker.name().title());
//            product.setSlug(StringHelper.toSlug(product.getName()));
//            product.setDescription(faker.lorem().sentence()); // text
//            product.setPrice(
//                    new BigDecimal(faker.number().numberBetween(100, 200) * 10000));
//            product.setCreatedBy("0");
//            product.setUpdatedBy("0");
//            product.setDetail(faker.lorem().sentence());
//            product.setThumbnails(faker.avatar().image());
//            product.setStatus(ProductSimpleStatus.ACTIVE);
//            listProduct.add(product);
//            System.out.println(product.toString());
//        }
//        productRepository.saveAll(listProduct);
//    }
//    private void seedOrder() {
//        List<Product> products = productRepository.findAll();
//        List<User> users = userRepository.findAll();
//        // Sinh danh sách order để lưu
//        List<Order> orders = new ArrayList<>();
//        for (int i = 0; i < numberOfOrder; i++) {
//            // Thông tin một order
//            Order order = new Order();
//            order.setUser(users.get(faker.number().numberBetween(0,users.size()-1)));
//            order.setUser_id(order.getUser().getUserId());
////            order.setUserId(); // chưa có userId, tạm thời để mặc định.
//            order.setId(UUID.randomUUID().toString());
//            // Generate order details.
//            Set<OrderDetail> orderDetails = new HashSet<>();
//            // sinh ngẫu nhiên số lượng order detail cho một đơn hàng.
//            int randomOrderDetailNumber = faker.number().numberBetween(1, 5);
//            HashSet<String> existingProductId = new HashSet<>();
//            for (int j = 0; j < randomOrderDetailNumber; j++) {
//                // Generate 1 item
//                OrderDetail orderDetail = new OrderDetail();
//                // lấy random sản phẩm từ trong danh sách.
//                Product randomProduct = products.get(
//                        faker.number().numberBetween(0, products.size() - 1));
//                // tránh tình trạng sản phẩm bị trùng trong đơn hàng.
//                if (existingProductId.contains(randomProduct.getId())) {
//                    // bỏ qua nếu trùng sản phẩm hoặc có thể lấy random lại một product khác.
//                    continue;
//                }
//                existingProductId.add(randomProduct.getId());
//                // tạo khoá chính từ order id và product id
//                orderDetail.setId(new OrderDetailId(order.getId(), randomProduct.getId()));
//                // set quan hệ với order
//                orderDetail.setOrder(order);
//                // set quan hệ với product
//                orderDetail.setProduct(randomProduct);
//                orderDetail.setUnitPrice(randomProduct.getPrice());
//                orderDetail.setQuantity(faker.number().numberBetween(1, 5));
//                // tính lại tổng tiền.
//                order.addTotalPrice(orderDetail);
//                // add vào danh sách order Detail
//                orderDetails.add(orderDetail);
//            }
//            // set orderDetails vào order
//            order.setOrderDetails(orderDetails);
//            order.setStatus(OrderSimpleStatus.DONE);
//
//            // Add order vào danh sách orders bên ngoài, để có thể save all.
//            orders.add(order);
//        }
//        orderRepository.saveAll(orders);
//    }
//
//
//
//    public static void main(String[] args) {
//
//    }
//}
