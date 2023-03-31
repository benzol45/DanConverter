package com.example.convertor.entity;

public enum LearnProgram {
    p1(1,"Оказание первой помощи пострадавшим","пострадавшим"),
    p2(2,"Использование (применение) средств индивидуальной защиты", "индивидуальной"),
    p3(3,"Общие вопросы охраны труда и функционирования системы управления охраной труда", "общие"),
    p4(4,"Безопасные методы и приемы выполнения работ при воздействии вредных и (или) опасных производственных факторов, источников опасности, идентифицированных в рамках специальной оценки условий труда и оценки профессиональных рисков","производственных"),
    //p5(5,""),
    p6(6,"Безопасные методы и приемы выполнения земляных работ","земляных"),
    p7(7,"Безопасные методы и приемы выполнения ремонтных, монтажных и демонтажных работ зданий и сооружений", "монтажных"),
    p8(8,"Безопасные методы и приемы выполнения работ при размещении, монтаже, техническом обслуживании и ремонте технологического оборудования (включая технологическое оборудование)","технологического"),
    p9(9,"Безопасные методы и приемы выполнения работ на высоте","высоте"),
    p10(10,"Безопасные методы и приемы выполнения пожароопасных работ","пожароопасных"),
    p11(11,"Безопасные методы и приемы выполнения работ в ограниченных и замкнутых пространствах (ОЗП)","замкнутых"),
    p12(12,"Безопасные методы и приемы выполнения строительных работ, в том числе: - окрасочные работы - электросварочные и газосварочные работы","строительных"),
    p13(13,"Безопасные методы и приемы выполнения работ, связанных с опасностью воздействия сильнодействующих и ядовитых веществ","сильнодействующих"),
    p14(14,"Безопасные методы и приемы выполнения газоопасных работ","газоопасных"),
    p15(15,"Безопасные методы и приемы выполнения огневых работ","огневых"),
    p16(16,"Безопасные методы и приемы выполнения работ, связанные с эксплуатацией подъемных сооружений","подъемных"),
    p17(17,"Безопасные методы и приемы выполнения работ, связанные с эксплуатацией тепловых энергоустановок","тепловых"),
    p18(18,"Безопасные методы и приемы выполнения работ в электроустановках","электроустановках"),
    p19(19,"Безопасные методы и приемы выполнения работ, связанные с эксплуатацией сосудов, работающих под избыточным давлением","сосудов"),
    p20(20,"Безопасные методы и приемы обращения с животными","животными"),
    p21(21,"Безопасные методы и приемы при выполнении водолазных работ","водолазных"),
    p22(22,"Безопасные методы и приемы работ по поиску, идентификации, обезвреживанию и уничтожению взрывоопасных предметов","взрывоопасных"),
    p23(23,"Безопасные методы и приемы работ в непосредственной близости от полотна или проезжей части эксплуатируемых автомобильных и железных дорог","полотна"),
    p24(24,"Безопасные методы и приемы работ, на участках с патогенным заражением почвы","патогенным"),
    p25(25,"Безопасные методы и приемы работ по валке леса в особо опасных условиях","валке"),
    p26(26,"Безопасные методы и приемы работ по перемещению тяжеловесных и крупногабаритных грузов при отсутствии машин соответствующей грузоподъемности и разборке покосившихся и опасных (неправильно уложенных) штабелей круглых лесоматериалов","тяжеловесных"),
    p27(27,"Безопасные методы и приемы работ с радиоактивными веществами и источниками ионизирующих излучений", "радиоактивными"),
    p28(28,"Безопасные методы и приемы работ с ручным инструментом, в том числе с пиротехническим","ручным"),
    p29(29,"Безопасные методы и приемы работ в театрах","театрах");


    private int id;
    private String title;
    private String patternWord;

    LearnProgram(int id, String title, String patternWord) {
        this.id = id;
        this.title = title;
        this.patternWord = patternWord;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPatternWord() {
        return patternWord;
    }
}
