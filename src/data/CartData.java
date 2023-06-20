package data;

import java.util.*;

public class CartData {
    //총 입력 데이터
    List<Map<String, Object>> cartData = new ArrayList<>();

    /**
     * 메뉴 추가
     */
    public void setCartData(String menuName, int menuPrice, int pcs, String option1, String option2) {
        if (cartData.size() == 0) {
            inputCartData(menuName, menuPrice, pcs, option1, option2);
        } else {
            checkCartData(menuName, menuPrice, pcs, option1, option2);
        }
    }

    /**
     * 데이터 추가 코드
     * @param menuName
     * @param menuPrice
     * @param pcs
     * @param option1
     * @param option2
     */
    public void inputCartData(String menuName, int menuPrice, int pcs, String option1, String option2) {
        Map<String, Object> data = new HashMap<>();
        data.put("menuName", menuName);
        data.put("menuPrice", menuPrice);
        data.put("pcs", pcs);
        if (option1 != null) {
            data.put("option1", option1);
        }
        if (option2 != null) {
            data.put("option2", option2);
        }
        this.cartData.add(data);
    }

    /**
     * 데이터가 있는지 검사하는 로직
     * 데이터가 있을 경우 숫자만 증가 시킨다.
     * @param menuName
     * @param menuPrice
     * @param pcs
     * @param option1
     * @param option2
     */
    public void checkCartData(String menuName, int menuPrice, int pcs, String option1, String option2) {
        int cnt = 0;
        for (Map<String, Object> data : cartData) {
            if (data.get("menuName").equals(menuName) && (Integer) data.get("menuPrice") == (menuPrice) && (Integer) data.get("pcs") == pcs
                    && data.get("option1").equals(option1) && data.get("option2").equals(option2)) {

                int beforeMenuPrice = (Integer) data.get("menuPrice");
                cartData.get(cnt).replace("pcs" , beforeMenuPrice + menuPrice);

                int beforePcs = (Integer) data.get("pcs");
                cartData.get(cnt).replace("pcs" , beforePcs + pcs);
            }
            cnt++;
        }
    }

    /**
     * 메뉴 전체 return String 으로 전송
     *  String \n 넣어서 보냈으니까 바로 textArrea에 append 사용 가능
     * @return
     */
    public String getCartData() {
        StringBuffer buffer = new StringBuffer();
        for (Map<String, Object> data : cartData) {
            buffer.append(data.get("menuName"));
            if (data.containsKey("option1")) {
                buffer.append(" ");
                buffer.append(data.get("option1"));
            }
            if (data.containsKey("option2")) {
                buffer.append(" ");
                buffer.append(data.get("option2"));
            }
            buffer.append(" ");
            buffer.append(data.get("pcs"));
            buffer.append("개");
            buffer.append("\n");
        }
        return buffer.toString();
    }

    /**
     * 메뉴 초기화
     */
    public void resetCartData() {
        this.cartData.clear();
    }

    /**
     * 결제 금액 전송
     *
     * @return
     */
    public int allPayCartData() {
        int pay = 0;
        for(Map<String , Object> data : cartData){
            pay += (Integer)data.get("pcs");
        }
        return pay;
    }
}