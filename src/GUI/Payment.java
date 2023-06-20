package GUI;

import JAVAinterface.OrderDevice;
import JAVAinterface.PaymentDevice;
import data.CartData;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment extends JFrame {
    private PaymentDevice paymentDevice;
    private OrderDevice orderDevice;
    private OrderManage orderManage;
    private CartData cartData;

    public Payment(OrderDevice orderDevice, OrderManage orderManage, CartData cartData) {
        this.paymentDevice = new PaymentDevice();
        this.orderDevice = orderDevice;
        this.orderManage = orderManage;
        this.cartData = cartData;

        setTitle("결제");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false); // 크기 조정 불가능

        // 상단 패널
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("결제");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        topPanel.add(titleLabel);
        add(topPanel, BorderLayout.NORTH);

        // 중앙 패널
        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 2x2 그리드 레이아웃, 가로와 세로 간격 설정
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // 여백 설정

        JLabel cardNumberLabel = new JLabel("카드번호:");
        JTextField cardNumberField = new JTextField();
        JLabel validityYearLabel = new JLabel("유효 년도:");
        JTextField validityYearField = new JTextField(4);
        JLabel validityMonthLabel = new JLabel("유효 월:");
        JTextField validityMonthField = new JTextField(2);

        // 숫자만 입력, 년도는 4글자, 1~12월만 선택 유효성 검사 필터 적용
        ((PlainDocument) cardNumberField.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        ((PlainDocument) validityYearField.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        ((PlainDocument) validityYearField.getDocument()).setDocumentFilter(new LengthLimitFilter(4));
        ((PlainDocument) validityMonthField.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        ((PlainDocument) validityMonthField.getDocument()).setDocumentFilter(new NumberRangeFilter(1, 12));

        centerPanel.add(cardNumberLabel);
        centerPanel.add(cardNumberField);
        centerPanel.add(validityYearLabel);
        centerPanel.add(validityYearField);
        centerPanel.add(validityMonthLabel);
        centerPanel.add(validityMonthField);
        add(centerPanel, BorderLayout.CENTER);

        // 하단 패널
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 가운데 정렬, 가로와 세로 간격 설정
        JButton paymentButton = new JButton("결제");
        JButton cancelButton = new JButton("취소");

        paymentButton.setBackground(new Color(51, 102, 255)); // 배경색 설정
        paymentButton.setForeground(Color.WHITE); // 글자색 설정
        paymentButton.setFocusPainted(false); // 선택 시 테두리 제거
        paymentButton.setPreferredSize(new Dimension(100, 40)); // 크기 설정
        paymentButton.setFont(paymentButton.getFont().deriveFont(Font.BOLD, 14)); // 글꼴 스타일 설정

        cancelButton.setBackground(new Color(255, 51, 51)); // 배경색 설정
        cancelButton.setForeground(Color.WHITE); // 글자색 설정
        cancelButton.setFocusPainted(false); // 선택 시 테두리 제거
        cancelButton.setPreferredSize(new Dimension(100, 40)); // 크기 설정
        cancelButton.setFont(cancelButton.getFont().deriveFont(Font.BOLD, 14)); // 글꼴 스타일 설정

        bottomPanel.add(paymentButton);
        bottomPanel.add(cancelButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // 결제 버튼 클릭 이벤트 처리
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 결제 버튼 클릭 시 동작 추가
                // 입력 값 가져오기
                int cardNumber = Integer.parseInt(cardNumberField.getText());
                int dateYYExpired = Integer.parseInt(validityYearField.getText());
                int dataMMExpired = Integer.parseInt(validityMonthField.getText());

                // 결제 요청
                boolean result = paymentDevice.sendPaymentReq(cardNumber, dateYYExpired, dataMMExpired);

                if (result) { //결제 성공 시
                    // 주문 번호 추가
                    int orderNumber = orderDevice.getOrderNumber();
                    orderManage.orderNumbers.add(orderNumber);
                    
                    int response = 0;

                    // 유효년도로 딜레이 적용
                    while( response != 2 ){
                        response = paymentDevice.receivePaymentRes();
                    }

                    // 딜레이가 끝나면 전광판에서 주문번호 제거
                    int orderIndex = orderManage.orderNumbers.indexOf(orderNumber);
                    if (orderIndex != -1) {
                        orderManage.orderNumbers.remove(orderIndex);
                    }

                    JOptionPane.showMessageDialog(null, "결제가 완료되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);

                    //장바구니 리셋
                    cartData.resetCartData();

                    dispose(); // 창 닫기

                } else { //결제 실패 시
                    JOptionPane.showMessageDialog(null, "카드 정보를 다시 확인하세요.", "알림", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // 취소 버튼 클릭 이벤트 처리
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 취소 버튼 클릭 시 동작 추가
                dispose(); // 창 닫기
            }
        });

        pack(); // 컴포넌트 크기에 맞게 창 크기 조절
        setLocationRelativeTo(null); // 화면 중앙에 위치
        setVisible(true); // 창 표시
    }

    //유효성 검사용 필터 클래스
    private class NumberOnlyFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            StringBuilder builder = new StringBuilder(string.replaceAll("\\D", ""));
            super.insertString(fb, offset, builder.toString(), attr);
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            StringBuilder builder = new StringBuilder(text.replaceAll("\\D", ""));
            super.replace(fb, offset, length, builder.toString(), attrs);
        }
    }

    static class LengthLimitFilter extends DocumentFilter {
        private final int maxLength;

        public LengthLimitFilter(int maxLength) {
            this.maxLength = maxLength;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            if (fb.getDocument().getLength() + string.length() <= maxLength) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            if (fb.getDocument().getLength() - length + text.length() <= maxLength) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    static class NumberRangeFilter extends DocumentFilter {
        private final int min;
        private final int max;

        public NumberRangeFilter(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                throws BadLocationException {
            StringBuilder builder = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            builder.insert(offset, string);

            if (isInRange(builder.toString())) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {
            StringBuilder builder = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
            builder.replace(offset, offset + length, text);

            if (isInRange(builder.toString())) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isInRange(String text) {
            try {
                int value = Integer.parseInt(text);
                return value >= min && value <= max;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
