package GUI;

import data.CartData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Options extends JFrame {
    private String menuName;
    private String menuPrice; // Int로 형변환 필요
    private String menuCategory;
    private int pcs; // 개수
    private boolean isShotButtonClicked = false;

    private int totalPrice; // 옵션 포함 가격

    private String option1; // 샷추가 여부
    private String option2; // 아이스 or 핫

    private CartData cartData;

    public Options(CartData cartData, String menuName, String menuPrice, String menuCategory) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.menuCategory = menuCategory;
        this.pcs = 1;
        this.option1 = null;
        this.option2 = null;
        this.cartData = cartData;

        setTitle("세부 옵션");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 제목 레이블
        JLabel titleLabel = new JLabel("세부 옵션 - " + menuName);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 28)); // 폰트 크기를 28로 설정
        add(titleLabel, BorderLayout.NORTH);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        JPanel shotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel iceHotPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // 커피 or 라떼 카테고리만 샷추가 표시
        if(menuCategory == "coffee" || menuCategory == "latte"){
            JButton shotButton = new JButton("샷 추가 + 500원");
            shotButton.setPreferredSize(new Dimension(200, 40));
            shotButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!isShotButtonClicked) {
                        shotButton.setBackground(Color.DARK_GRAY);
                        shotButton.setForeground(Color.WHITE);
                        isShotButtonClicked = true;
                        setOption1("샷 추가");
                        calTotalPrice(menuPrice);
                    } else {
                        shotButton.setBackground(null);
                        shotButton.setForeground(Color.BLACK);
                        isShotButtonClicked = false;
                        setOption1(null);
                        setTotalPrice(menuPrice);
                    }
                }
            });
            shotPanel.add(shotButton);
        }

        //와플, 베이커리, etc 제외 기본값 ice로 초기화
        if(menuCategory != "waffle" && menuCategory != "bakery" && menuCategory != "etc"){
            setOption2("Ice");
        }

        //커피, 라떼, 티는 ice or hot
        if(menuCategory == "coffee" || menuCategory == "latte" || menuCategory == "tea"){
            JButton iceButton = new JButton("ICE");
            JButton hotButton = new JButton("HOT");
            iceButton.setPreferredSize(new Dimension(100, 40));
            hotButton.setPreferredSize(new Dimension(100, 40));

            iceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    iceButton.setBackground(Color.DARK_GRAY);
                    iceButton.setForeground(Color.WHITE);
                    hotButton.setBackground(null);
                    hotButton.setForeground(Color.BLACK);
                    setOption2("Ice");
                }
            });
            hotButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hotButton.setBackground(Color.DARK_GRAY);
                    hotButton.setForeground(Color.WHITE);
                    iceButton.setBackground(null);
                    iceButton.setForeground(Color.BLACK);
                    setOption2("Hot");
                }
            });
            iceHotPanel.add(iceButton);
            iceHotPanel.add(hotButton);
        }

        // 수량 관련 TextField와 버튼
        JTextField quantityTextField = new JTextField(10);
        quantityTextField.setEditable(false);
        quantityTextField.setHorizontalAlignment(SwingConstants.CENTER); // 가운데 정렬 설정
        quantityTextField.setText("1");
        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        plusButton.setPreferredSize(new Dimension(50, 30));
        minusButton.setPreferredSize(new Dimension(50, 30));

        quantityPanel.add(minusButton);
        quantityPanel.add(quantityTextField);
        quantityPanel.add(plusButton);

        buttonPanel.add(shotPanel);
        buttonPanel.add(iceHotPanel);
        buttonPanel.add(quantityPanel);

        add(buttonPanel, BorderLayout.CENTER);

        // 하단 버튼 패널
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addToCartButton = new JButton("담기");
        JButton cancelButton = new JButton("취소");

        bottomPanel.add(addToCartButton);
        bottomPanel.add(cancelButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // 수량 관련 버튼 동작
        plusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pcs < 10){
                    pcs++;
                    quantityTextField.setText(Integer.toString(pcs));
                }
            }
        });

        minusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pcs > 1) {
                    pcs--;
                    quantityTextField.setText(Integer.toString(pcs));
                }
            }
        });

        // 담기 버튼
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 담기 버튼 클릭 시 동작 추가
                cartData.inputCartData(menuName, totalPrice, pcs, option1, option2);
                System.out.println(cartData.getCartData());
                dispose();
            }
        });

        // 취소 버튼
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 창 닫기
            }
        });

        // 창 가운데 정렬
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getPcs() {
        return pcs;
    }

    public void setPcs(int pcs) {
        this.pcs = pcs;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String menuPrice) {
        int totalPrice = convertPriceToInt(menuPrice);

        this.totalPrice = totalPrice;
    }

    public void calTotalPrice(String menuPrice) {
        // ex) 2,500원 -> 2500 + 500(샷 추가 가격)

        int price = convertPriceToInt(menuPrice);

        // 샷 추가 가격
        price += 500;

        this.totalPrice = price;
    }

    public int convertPriceToInt(String priceString) {
        // 문자열에서 쉼표(,)를 제거하고 숫자 부분만 남기기
        String numberString = priceString.replaceAll("[^\\d]", "");

        // 문자열을 정수로 변환
        int price = Integer.parseInt(numberString);

        return price;
    }
}