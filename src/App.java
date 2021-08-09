class App {
  public static void main(String[] args) throws Exception {
    
    Time t1 = new Time();
    // representação string, padrão 00:00:00
    System.out.println("1");
    System.out.println(t1.toString().equals("00:00:00"));
    Time t2 = new Time(1, 40, 5);
    System.out.println("2");
    System.out.println(t2.toString().equals("01:40:05"));
    Time t3 = t1.plus(t2);
    System.out.println("3");
    System.out.println(t3.toString().equals("01:40:05"));
    System.out.println("4");
    System.out.println(t3.hours() == 1);
    System.out.println("5");
    System.out.println(t3.minutes() == 40);
    System.out.println("6");
    System.out.println(t3.seconds() == 5);
    // deve ser imutável
    System.out.println("7");
    System.out.println(t1.hours() == 0);
    System.out.println("8");
    System.out.println(t1.minutes() == 0);
    System.out.println("9");
    System.out.println(t1.seconds() == 0);
    // // plus
    Time t4 = t3.plus(t2);
    System.out.println("10");
    System.out.println(t4);
    System.out.println(t4.toString().equals("03:20:10"));
    // implementar equals
    System.out.println("11");
    System.out.println(t4.equals(new Time(3, 20, 10)));
    Time t5 = t2.plusHours(1);
    System.out.println("12");
    System.out.println(t5.toString().equals("02:40:05"));
    Time t6 = t4.plusHours(23);
    System.out.println("13");
    System.out.println(t6.toString().equals("02:20:10"));
    Time t7 = t6.plusMinutes(10);
    //System.out.println(t6);
    //System.out.println(t7);
    System.out.println("14");
    System.out.println(t7.toString().equals("02:30:10"));
    Time t8 = t7.plusSeconds(80);
    System.out.println("15");
    System.out.println(t8.toString().equals("02:31:30"));
    Time t9 = new Time().plusHours(19).plusMinutes(23).plusSeconds(18);
    System.out.println("16");
    System.out.println(t9.toString().equals("19:23:18"));
    Time t10 = t9.plusHours(-1).plusMinutes(-1).plusSeconds(-1);
    System.out.println("17");
    System.out.println(t10.toString().equals("18:22:17"));
    Time t11 = t10.minusHours(2).minusMinutes(2).minusSeconds(2);
    System.out.println("18");
    System.out.println(t11.toString().equals("16:20:15"));
    Time t12 = t11.minusHours(-5);
    System.out.println("19");
    System.out.println(t12.toString().equals("21:20:15"));
    System.out.println(t11);
    System.out.println(t12);
    Time t13 = t11.minus(t12);
    System.out.println("20");
    System.out.println(t13.toString().equals("19:00:00"));
    System.out.println(t13.isMidDay() == false);
    Time t14 = t13.minus(t13);
    System.out.println(t14.toString().equals("00:00:00"));
    System.out.println(t14.isMidDay() == false);
    System.out.println(t14.isMidNight() == true);
    System.out.println(t14.plusHours(12).isMidDay() == true);
    Time t15 = new Time(3, 40);
    System.out.println(t15.toString().equals("03:40:00"));
    Time t16 = t15.shift();
    System.out.println(t16.toString().equals("15:40:00"));
    Time t17 = t16.shift();
    System.out.println(t17.toString().equals("03:40:00"));
    Time t18 = t17.tick();
    System.out.println(t18.toString().equals("03:40:01"));
    Time t19 = t18.tick().tick().tick();
    System.out.println(t19.toString().equals("03:40:04"));
    Time t20 = t19.plusHours(50).plusMinutes(50).minusSeconds(50).tick().shift();
    System.out.println(t20.toString().equals("18:29:15"));


    Time tr1 = new Time(9, 40, 15);
    // representação string, padrão 00:00:00
    System.out.println(tr1.toString().equals("09:40:15"));
    // representação string com formato alternativo
    System.out.println(tr1.toLongString().equals("9 horas 40 minutos e 15 segundos"));
    // fromString, formato 00:00:00
    Time tr2 = Time.fromString("01:36:00");
    System.out.println(tr2.toLongString().equals("1 hora e 36 minutos"));
    // fromDouble
    Time tr3 = Time.fromDouble(3.824);
    System.out.println(tr3.toLongString().equals("3 horas 49 minutos e 26 segundos"));
    // sem arredondamentos
    System.out.println(Time.fromDouble(17.1447).toLongString().equals("17 horas 8 minutos e 40 segundos"));
    // fromSeconds
    Time tr4 = Time.fromSeconds(76632);
    //System.out.println(tr4.toLongString().equals("21 horas 15 minutos e 32 segundos")); // PATCH:
    System.out.println(tr4.toLongString().equals("21 horas 17 minutos e 12 segundos"));
    // --------------------------------------------------------------------------------
    System.out.println(Time.fromSeconds(68400).toLongString());
    System.out.println(Time.fromSeconds(68400).toLongString().equals("19 horas"));
    // toDouble

    // // PATCH
    // // Time tr4 = Time.fromString("16:45:11"); // DEVE SER: (causa erro por redeclaração de tr4)
    // tr4 = Time.fromString("16:45:11"); //

    System.out.println(tr4.toDouble()); // 16.75305556 aproximadamente
    System.out.println(Time.fromString("13:04:59").toDouble()); // 13.08305556 aproximadamente

    double tr5double = Time.fromString("13:04:59").toDouble();
    Time tr5 = Time.fromDouble(tr5double);
    System.out.println(tr5.toLongString());
    System.out.println(tr5.toLongString().equals("13 horas 4 minutos e 59 segundos"));
    // // fromTime
     Time tr6 = Time.from(tr5);
    // // toShortString
    System.out.println(tr6.toShortString().equals("13h04m59s"));
    System.out.println(Time.fromString("15:03:00").toShortString().equals("15h03m"));
    // // System.out.println(Time.fromString("05:00:01").toShortString().equals("15h00m01s")); // PATCHED:
    System.out.println(Time.fromString("15:00:01").toShortString().equals("15h00m01s"));
    // // constantes
    Time tr7 = Time.MIDDAY;
    System.out.println(tr7.toShortString().equals("12h"));
    Time tr8 = Time.MIDNIGHT;
    System.out.println(tr8.toShortString().equals("00h"));
    System.out.println(Time.MIDDAY.toInt() == 43200);
    System.out.println(Time.MIDNIGHT.toInt() == 0);


    // // construtores:
    LatLong c1 = new LatLong();
    System.out.println(c1.latitude == 0.0);
    System.out.println(c1.longitude == 0.0);

    LatLong c2 = new LatLong(50.0, 134.0);
    System.out.println(c2.latitude == 50.0);
    System.out.println(c2.longitude == 134.0);

    LatLong c3 = new LatLong(-90.0, -180.0);
    System.out.println(c3.latitude == -90.0);
    System.out.println(c3.longitude == -180.0);

    // // estas coordenadas são inválidas e devem lançar exceção
    // // faça serem rejeitadas e depois comente-as para não parar o programa
    new LatLong(-91.0, 0.0);
    new LatLong(100.0, 0.0);
    new LatLong(10.0, -182.0);
    new LatLong(10.0, 200.0);
    new LatLong(-95.0, -200.0);

    // // imutabilidade: as linhas a seguir devem causar erro de compilação
    // // verifique se está de acordo e depois comente-as
    LatLong c4 = new LatLong();
    //c4.latitude = 30.0;  // não deve permitir reatribuição
    //c4.longitude = 80.0; // não deve permitir reatribuição

    // operações/comandos:
    LatLong in = new LatLong(30.0, 50.0);
    LatLong out = in.norte(5.0);
    System.out.println(in.latitude == 30.0); // deve ser imutável
    System.out.println(out.latitude == 35.0);
    out.norte(5.0); // sem reatribuição sem alteração
    System.out.println(out.latitude == 35.0);
    out = out.norte(5.0); // reatribuindo
    System.out.println(out.latitude == 40.0);
    out = out.sul(60.0);
    System.out.println(out.latitude == -20.0);
    out = out.sul(30.0);
    System.out.println(out.latitude == -50.0);
    out = out.sul(-10.0);
    System.out.println(out.latitude == -40.0);
    out = out.norte(-10.0);
    System.out.println(out.latitude == -50.0);
    System.out.println(out.longitude == 50.0);
    out = out.leste(50.0);
    System.out.println(out.longitude == 100.0);
    out = out.oeste(180.0);
    System.out.println(out.longitude == -80.0);
    out = out.oeste(-10.0);
    System.out.println(out.longitude == -70.0);
    out = out.leste(-10.0);
    System.out.println(out.longitude == -80.0);

    // consultas:
    LatLong q = new LatLong();
    System.out.println(q.latitude == 0);
    System.out.println(q.longitude == 0);
    System.out.println(q.noEquador() == true);
    System.out.println(q.emGreenwich() == true);
    q = q.norte(10.0);
    System.out.println(q.latitude == 10);
    System.out.println(q.noEquador() == false);
    q = q.leste(10.0);
    System.out.println(q.emGreenwich() == false);
    q = q.leste(170.0);
    System.out.println(q.longitude == 180.0);
    System.out.println(q.emGreenwich() == false);
    q = q.oeste(200.0);
    System.out.println(q.longitude == -20.0);
    System.out.println(q.emGreenwich() == false);
    q = q.leste(5.0).leste(5.0).leste(5.0).leste(5.0);
    System.out.println(q.longitude == 0.0);
    System.out.println(q.emGreenwich() == true);

    LatLong r = new LatLong(30.0, 70.0);
    System.out.println(r.latitude == 30.0);
    System.out.println(r.longitude == 70.0);
    System.out.println(r.noNorte() == true);
    System.out.println(r.noSul() == false);
    System.out.println(r.noOriente() == true);
    System.out.println(r.noOcidente() == false);
    r = r.oeste(140.0).sul(60.0);
    System.out.println(r.latitude == -30.0);
    System.out.println(r.longitude == -70.0);
    System.out.println(r.noNorte() == false);
    System.out.println(r.noSul() == true);
    System.out.println(r.noOriente() == false);
    System.out.println(r.noOcidente() == true);

    // As coordenadas entram em graus decimais com até quatro casas decimais
    LatLong coord1 = new LatLong(12.5334, 77.2635);
    System.out.println(coord1.toString()); // 12.5334, 77.2635
    System.out.println(coord1.toString().equals("12.5334, 77.2635"));
    // Não confunda o símbolo de grau ° com o de número ordinal º
    System.out.println(coord1.toGrausDecimais()); // 12.5334°N, 77.2635°E
    System.out.println(coord1.toGrausDecimais().equals("12.5334°N, 77.2635°E"));
    // Em Graus, Minutos e Segundos com 3 decimais
    System.out.println(coord1.toGrausMinutosSegundos()); // 12°32'0.240"N, 77°15'48.600"E
    System.out.println(coord1.toGrausMinutosSegundos().equals("12°32'0.240\"N, 77°15'48.600\"E"));

    LatLong coord2 = new LatLong(-75.9923, -52.1425);
    System.out.println(coord2.toString()); // -75.9923, -52.1425
    System.out.println(coord2.toString().equals("-75.9923, -52.1425"));
    System.out.println(coord2.toGrausDecimais()); // 75.9923°S, 52.1425°W
    System.out.println(coord2.toGrausDecimais().equals("75.9923°S, 52.1425°W"));
    System.out.println(coord2.toGrausMinutosSegundos()); // 75°59'32.280"S, 52°8'33.000"W
    System.out.println(coord2.toGrausMinutosSegundos().equals("75°59'32.280\"S, 52°8'33.000\"W"));

    // LatLong coord3 = LatLong.fromString("63°18'12.300\"S, 76°56'43.120\"W"); // INCORRETO: PATCH
    LatLong coord3 = LatLong.fromString("63°18'12.240\"S, 76°56'43.080\"W");
    System.out.println(coord3.toString()); // -63.3034, -76.9453
    System.out.println(coord3.toString().equals("-63.3034, -76.9453")); // -63.3034, -76.9453

    LatLong coord4 = LatLong.fromString("-63.3034, -76.9453");
    // System.out.println(coord4.toGrausMinutosSegundos().equals("63°18'12.300\"S, 76°56'43.120\"W")); // INCORRETO, PATCH:
    System.out.println(coord4.toGrausMinutosSegundos().equals("63°18'12.240\"S, 76°56'43.080\"W"));
  }
}
