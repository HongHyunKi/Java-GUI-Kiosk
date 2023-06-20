package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OrderManage extends JFrame {
    public JLabel titleLabel;
    public JPanel orderPanel;

    public JLabel orderNumberLabel;

    //주문번호 리스트
    public List<Integer> orderNumbers = new ArrayList<Integer>();

    public OrderManage() {
        setTitle("주문 대기 번호");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // 크기 조정 불가능

        // 상단 제목 라벨
        titleLabel = new JLabel("주문 번호");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24)); // 크기 조절 및 진하게 설정
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // 중앙 주문번호 표시 패널
        orderPanel = new JPanel(new GridLayout(3, 3));
        orderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 패널 내부 여백 설정

        // 전광판 주문번호 표시
        orderNumberLabel = new JLabel();

        orderNumberLabel.setFont(orderNumberLabel.getFont().deriveFont(Font.BOLD, 48)); // 크기 조절 및 진하게 설정
        orderNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderNumberLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // 테두리 설정

        orderPanel.add(orderNumberLabel);
        add(orderPanel, BorderLayout.CENTER);

        //데몬 쓰레드
        updateOrderNumberToGUI();

        pack(); // 컴포넌트 크기에 맞게 창 크기 조절
        setSize(400, 400); // 창 크기 설정
        setLocationRelativeTo(null); // 화면 중앙에 위치
        setVisible(true); // 창 표시
    }

    public List<Integer> getOrderNumbers() {
        return orderNumbers;
    }

    public void setOrderNumbers(List<Integer> orderNumbers) {
        this.orderNumbers = orderNumbers;
    }

    public String printOrderNumbers(){
        String temp = "";

        for(int i : orderNumbers){
            temp = temp +" "+ i;
        }

        return temp;
    }

    // 전광판 데몬 쓰레드
    private void updateOrderNumberToGUI() {
        Thread thread = new Thread(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                while (true) {
                    try {
                        //렉 방지 시간제한
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    int size = orderNumbers.size();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if ( count != size ) {
                                orderNumberLabel.setText(printOrderNumbers());
                                count = size;
                            }
                        }
                    });
                }
            }
        });
        thread.start();
    }
}
