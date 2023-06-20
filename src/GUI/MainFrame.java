package GUI;

import JAVAinterface.OrderDevice;
import data.CartData;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JPanel {
    private JPanel tab1;
    private JPanel tab2;
    private JPanel tab3;
    private JPanel tab4;
    private JPanel tab5;
    private JPanel tab6;
    private JPanel tab7;
    private JPanel tab8;
    private JPanel tab9;
    private JPanel tab10;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JButton tapButton;
    private JTextArea cartTextArea;

    // 장바구니 데이터 담는 객체
    private CartData cartData;

    // 주문번호 클래스
    private OrderDevice orderDevice;

    // 전광판
    private OrderManage orderManage;

    public MainFrame() {
        setLayout(new BorderLayout());
        this.cartData = new CartData();
        this.orderDevice = new OrderDevice();
        this.orderManage = new OrderManage();

        // 첫 번째 탭
        tab1 = new JPanel();
        tab1.setLayout(new GridBagLayout());
        addButtonsToPanel(tab1, 13, getCoffeeMenu());

        // 두 번째 탭
        tab2 = new JPanel();
        tab2.setLayout(new GridBagLayout());
        addButtonsToPanel(tab2, 11, getLatteMenu());

        // 세 번째 탭
        tab3 = new JPanel();
        tab3.setLayout(new GridBagLayout());
        addButtonsToPanel(tab3, 13, getTeaMenu());

        // 네 번째 탭
        tab4 = new JPanel();
        tab4.setLayout(new GridBagLayout());
        addButtonsToPanel(tab4, 5, getAdeMenu());

        // 다섯 번째 탭
        tab5 = new JPanel();
        tab5.setLayout(new GridBagLayout());
        addButtonsToPanel(tab5, 5, getSmoothieMenu());

        // 여섯 번째 탭
        tab6 = new JPanel();
        tab6.setLayout(new GridBagLayout());
        addButtonsToPanel(tab6, 7, getFrappeMenu());

        // 일곱 번째 탭
        tab7 = new JPanel();
        tab7.setLayout(new GridBagLayout());
        addButtonsToPanel(tab7, 4, getJuiceMenu());

        // 여덟 번째 탭
        tab8 = new JPanel();
        tab8.setLayout(new GridBagLayout());
        addButtonsToPanel(tab8, 7, getWaffleMenu());

        // 아홉 번째 탭
        tab9 = new JPanel();
        tab9.setLayout(new GridBagLayout());
        addButtonsToPanel(tab9, 13, getBakeryMenu());

        // 열 번째 탭
        tab10 = new JPanel();
        tab10.setLayout(new GridBagLayout());
        addButtonsToPanel(tab10, 5, getEtcMenu());

        // 카드 패널과 카드 레이아웃 생성
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // 탭을 카드 패널에 추가
        cardPanel.add(tab1, "COFFEE");
        cardPanel.add(tab2, "LATTE");
        cardPanel.add(tab3, "TEA");
        cardPanel.add(tab4, "ADE");
        cardPanel.add(tab5, "SMOOTHIE");
        cardPanel.add(tab6, "FRAPPE");
        cardPanel.add(tab7, "JUICE");
        cardPanel.add(tab8, "WAFFLE");
        cardPanel.add(tab9, "BAKERY");
        cardPanel.add(tab10, "ETC");

        // 상단 버튼
        JButton button1 = new JButton("COFFEE");
        JButton button2 = new JButton("LATTE");
        JButton button3 = new JButton("TEA");
        JButton button4 = new JButton("ADE");
        JButton button5 = new JButton("SMOOTHIE");
        JButton button6 = new JButton("FRAPPE");
        JButton button7 = new JButton("JUICE");
        JButton button8 = new JButton("WAFFLE");
        JButton button9 = new JButton("BAKERY");
        JButton button10 = new JButton("ETC");

        // 버튼 UI 설정
        button1.setUI(new BasicButtonUI());
        button2.setUI(new BasicButtonUI());
        button3.setUI(new BasicButtonUI());
        button4.setUI(new BasicButtonUI());
        button5.setUI(new BasicButtonUI());
        button6.setUI(new BasicButtonUI());
        button7.setUI(new BasicButtonUI());
        button8.setUI(new BasicButtonUI());
        button9.setUI(new BasicButtonUI());
        button10.setUI(new BasicButtonUI());

        // 버튼 배경색과 텍스트 색 설정
        Color brownColor = new Color(139, 69, 19);
        button1.setBackground(brownColor); // 갈색 (Brown)
        button1.setForeground(Color.WHITE); // 흰색 (White)
        button2.setBackground(brownColor); // 갈색 (Brown)
        button2.setForeground(Color.WHITE); // 흰색 (White)
        button3.setBackground(brownColor); // 갈색 (Brown)
        button3.setForeground(Color.WHITE); // 흰색 (White)
        button4.setBackground(brownColor); // 갈색 (Brown)
        button4.setForeground(Color.WHITE); // 흰색 (White)
        button5.setBackground(brownColor); // 갈색 (Brown)
        button5.setForeground(Color.WHITE); // 흰색 (White)
        button6.setBackground(brownColor); // 갈색 (Brown)
        button6.setForeground(Color.WHITE); // 흰색 (White)
        button7.setBackground(brownColor); // 갈색 (Brown)
        button7.setForeground(Color.WHITE); // 흰색 (White)
        button8.setBackground(brownColor); // 갈색 (Brown)
        button8.setForeground(Color.WHITE); // 흰색 (White)
        button9.setBackground(brownColor); // 갈색 (Brown)
        button9.setForeground(Color.WHITE); // 흰색 (White)
        button10.setBackground(brownColor); // 갈색 (Brown)
        button10.setForeground(Color.WHITE); // 흰색 (White)

        // 버튼 클릭 이벤트 처리
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button1.setBackground(Color.WHITE); // 흰색 (White)
                button1.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "COFFEE");
                tapButton = button1;
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button2.setBackground(Color.WHITE); // 흰색 (White)
                button2.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "LATTE");
                tapButton = button2;
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button3.setBackground(Color.WHITE); // 흰색 (White)
                button3.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "TEA");
                tapButton = button3;
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button4.setBackground(Color.WHITE); // 흰색 (White)
                button4.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "ADE");
                tapButton = button4;
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button5.setBackground(Color.WHITE); // 흰색 (White)
                button5.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "SMOOTHIE");
                tapButton = button5;
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button6.setBackground(Color.WHITE); // 흰색 (White)
                button6.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "FRAPPE");
                tapButton = button6;
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button7.setBackground(Color.WHITE); // 흰색 (White)
                button7.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "JUICE");
                tapButton = button7;
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button8.setBackground(Color.WHITE); // 흰색 (White)
                button8.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "WAFFLE");
                tapButton = button8;
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button9.setBackground(Color.WHITE); // 흰색 (White)
                button9.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "BAKERY");
                tapButton = button9;
            }
        });

        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButtonColors();
                button10.setBackground(Color.WHITE); // 흰색 (White)
                button10.setForeground(Color.BLACK); // 검은색 (Black)
                cardLayout.show(cardPanel, "ETC");
                tapButton = button10;
            }
        });

        // 상단 패널에 버튼과 제목 추가
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(" 꿈나눔 까페");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 24)); // 크기 조절 및 진하게 설정
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(2, 5));
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        buttonPanel.add(button6);
        buttonPanel.add(button7);
        buttonPanel.add(button8);
        buttonPanel.add(button9);
        buttonPanel.add(button10);
        topPanel.add(buttonPanel, BorderLayout.CENTER);

        // 전체 프레임에 상단 패널 추가
        add(topPanel, BorderLayout.NORTH);

        // 전체 프레임에 카드 패널 추가
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setPreferredSize(new Dimension(600, 800)); // 스크롤 영역 크기 조절
        add(scrollPane, BorderLayout.CENTER);

        // 결제하기 버튼
        JButton paymentButton = new JButton("결제하기");
        paymentButton.setBackground(Color.BLUE); // 파란색 (Blue)
        paymentButton.setForeground(Color.WHITE); // 흰색 (White)
        paymentButton.setPreferredSize(new Dimension(150, 50));
        paymentButton.setFont(paymentButton.getFont().deriveFont(Font.BOLD, 18)); // 크기 조절 및 진하게 설정
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 결제하기 버튼 클릭 시 동작 추가
                if(cartData.getCartData().length() == 0){
                    JOptionPane.showMessageDialog(null, "장바구니가 비었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                new Payment(orderDevice, orderManage, cartData); // 결제 창 생성
            }
        });

        // 장바구니 비우기 버튼
        JButton clearCartButton = new JButton("장바구니 비우기");
        clearCartButton.setBackground(new Color(0, 128, 0)); // 진한 초록색 (Dark Green)
        clearCartButton.setForeground(Color.WHITE); // 흰색 (White)
        clearCartButton.setPreferredSize(new Dimension(150, 50));
        clearCartButton.setFont(clearCartButton.getFont().deriveFont(Font.BOLD, 18));
        clearCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //장바구니 비우기
                cartData.resetCartData();
            }
        });
        // 하단 버튼 패널
        JPanel buttonPanel2 = new JPanel(new GridLayout(1, 2));
        buttonPanel2.add(paymentButton);
        buttonPanel2.add(clearCartButton);

        // 하단 패널에 버튼 추가
        JPanel bottomPanel = new JPanel(new BorderLayout());
        cartTextArea = new JTextArea();
        cartTextArea.setEditable(false);
        JScrollPane cartScrollPane = new JScrollPane(cartTextArea);
        cartScrollPane.setPreferredSize(new Dimension(600, 100));
        bottomPanel.add(cartScrollPane, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel2, BorderLayout.SOUTH);

        // 전체 프레임에 하단 패널 추가
        add(bottomPanel, BorderLayout.SOUTH);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 1080); // 프레임 크기 조절
        frame.getContentPane().add(this);
        frame.setVisible(true);

        //데몬 쓰레드
        updateCartDataToGUI();
    }

    private void addButtonsToPanel(JPanel panel, int buttonCount, String[][] menu) {
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Buttons fill the layout
        gbc.weightx = 1.0; // Horizontal component resizing
        gbc.weighty = 1.0; // Vertical component resizing
        gbc.insets = new Insets(10, 10, 10, 10); // Button spacing

        int rowCount = 0; // Current row count
        int columnCount = 0; // Current column count

        int size = menu.length;
        String menuCategory = menu[size-1][1];

        // 메뉴 버튼들
        for (int i = 0; i < menu.length-1; i++) {
            String menuName = menu[i][0];
            String menuPrice = menu[i][1];

            JButton button = new JButton("<html><center><div style='text-align: center;'>" + menu[i][0] + "<br>" + menu[i][1] + "</div></center></html>");
            button.setPreferredSize(new Dimension(150, 100));
            button.setMinimumSize(new Dimension(150, 100));
            button.setMaximumSize(new Dimension(150, 100));
            gbc.gridx = columnCount;
            gbc.gridy = rowCount;
            panel.add(button, gbc);

            columnCount++;

            // 현재 행에 버튼가 3개 있으면 다음 행으로 이동
            if (columnCount == 3) {
                columnCount = 0;
                rowCount++;
            }

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // 담기 버튼 클릭 시 동작 추가
                    new Options(cartData, menuName, menuPrice, menuCategory); // 결제 창 생성
                }
            });
        }
    }

    private void resetButtonColors() {
        if (tapButton != null) {
            tapButton.setBackground(new Color(139, 69, 19)); // 갈색 (Brown)
            tapButton.setForeground(Color.WHITE); // 흰색 (White)
        }
    }

    private String[][] getCoffeeMenu() {
        String[][] menu = {
                {"아메리카노", "1,500원"},
                {"카페라떼", "2,000원"},
                {"카푸치노", "2,000원"},
                {"에스프레소", "1,800원"},
                {"더치커피", "3,000원"},
                {"바닐라라떼", "2,500원"},
                {"카라멜마끼아또", "2,500원"},
                {"모카라떼", "2,500원"},
                {"카라멜모카", "2,500원"},
                {"헤이즐넛 라떼", "2,500원"},
                {"Category", "coffee"}
        };
        return menu;
    }

    private String[][] getLatteMenu() {
        String[][] menu = {
                {"밀크티", "2,500원"},
                {"그린티라떼", "2,800원"},
                {"레드빈라떼", "3,000원"},
                {"마스카포네라떼", "2,800원"},
                {"티라미수라떼", "3,200원"},
                {"고구마라떼", "3,000원"},
                {"초코라떼", "2,500원"},
                {"녹차라떼", "2,800원"},
                {"고로쇠라떼", "3,000원"},
                {"블루베리라떼", "3,500원"},
                {"Category", "latte"}
        };
        return menu;
    }

    private String[][] getTeaMenu() {
        String[][] menu = {
                {"페퍼민트", "2,000원"},
                {"얼그레이", "2,000원"},
                {"레몬차", "2,000원"},
                {"자몽차", "2,500원"},
                {"청귤차", "2,500원"},
                {"딸기차", "2,500원"},
                {"복숭아차", "2,500원"},
                {"유자차", "2,500원"},
                {"밀크티", "2,800원"},
                {"고구마라떼", "3,000원"},
                {"Category", "tea"}
        };
        return menu;
    }

    private String[][] getAdeMenu() {
        String[][] menu = {
                {"레몬에이드", "2,500원"},
                {"자몽에이드", "2,800원"},
                {"청귤에이드", "2,800원"},
                {"블루베리에이드", "3,000원"},
                {"딸기에이드", "3,800원"},
                {"망고에이드", "3,800원"},
                {"키위에이드", "3,000원"},
                {"파인애플에이드", "3,700원"},
                {"오렌지에이드", "3,200원"},
                {"포도에이드", "3,200원"},
                {"Category", "ade"}
        };
        return menu;
    }

    private String[][] getSmoothieMenu() {
        String[][] menu = {
                {"딸기스무디", "3,500원"},
                {"블루베리스무디", "3,500원"},
                {"망고스무디", "4,000원"},
                {"키위스무디", "3,500원"},
                {"딸기바나나스무디", "5,000원"},
                {"캐럿스무디", "4,200원"},
                {"그린스무디", "4,000원"},
                {"오렌지스무디", "5,500원"},
                {"케일스무디", "4,300원"},
                {"초콜릿스무디", "5,800원"},
                {"Category", "smoothie"}
        };
        return menu;
    }

    private String[][] getFrappeMenu() {
        String[][] menu = {
                {"카페프라페", "3,500원"},
                {"모카프라페", "3,800원"},
                {"바닐라프라페", "3,800원"},
                {"카라멜프라페", "3,800원"},
                {"민트초코프라페", "3,800원"},
                {"스트로베리프라페", "3,800원"},
                {"사과프라페", "5,200원"},
                {"바나나프라페", "4,400원"},
                {"멜론프라페", "3,600원"},
                {"초코라페", "4,500원"},
                {"Category", "frappe"}
        };
        return menu;
    }

    private String[][] getJuiceMenu() {
        String[][] menu = {
                {"사과주스", "2,500원"},
                {"포도주스", "2,800원"},
                {"오렌지주스", "2,800원"},
                {"수박주스", "2,800원"},
                {"망고주스", "4,800원"},
                {"복숭아주스", "3,800원"},
                {"파인애플주스", "3,200원"},
                {"딸기주스", "4,200원"},
                {"감귤주스", "5,500원"},
                {"토마토주스", "4,100원"},
                {"Category", "juice"}
        };
        return menu;
    }

    private String[][] getWaffleMenu() {
        String[][] menu = {
                {"플레인와플", "3,000원"},
                {"딸기와플", "3,500원"},
                {"블루베리와플", "3,500원"},
                {"바나나와플", "3,500원"},
                {"체리와플", "3,500원"},
                {"녹차와플", "3,500원"},
                {"치즈와플", "3,500원"},
                {"딥치즈와플", "4,500원"},
                {"메이플와플", "3,700원"},
                {"초코와플", "3,500원"},
                {"Category", "waffle"}
        };
        return menu;
    }

    private String[][] getBakeryMenu() {
        String[][] menu = {
                {"크로와상", "2,000원"},
                {"도넛", "1,500원"},
                {"마카롱", "2,000원"},
                {"케이크", "3,000원"},
                {"머핀", "2,500원"},
                {"쿠키", "2,000원"},
                {"브라우니", "2,500원"},
                {"치즈케이크", "3,500원"},
                {"파운드케이크", "3,000원"},
                {"치즈머핀", "2,800원"},
                {"Category", "bakery"}
        };
        return menu;
    }

    private String[][] getEtcMenu() {
        String[][] menu = {
                {"초콜릿", "2,000원"},
                {"아이스크림", "2,500원"},
                {"요거트", "2,500원"},
                {"떡볶이", "3,500원"},
                {"라면", "3,500원"},
                {"콜라", "3,500원"},
                {"제로콜라", "3,500원"},
                {"사이다", "3,500원"},
                {"제로사이다", "3,500원"},
                {"레드불", "3,500원"},
                {"Category", "etc"}
        };
        return menu;
    }

    //GUI 업데이트하는 데몬 쓰레드
    private void updateCartDataToGUI() {
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

                    int size = cartData.getCartData().length();

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if ( count != size ) {
                                cartTextArea.setText(cartData.getCartData());
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