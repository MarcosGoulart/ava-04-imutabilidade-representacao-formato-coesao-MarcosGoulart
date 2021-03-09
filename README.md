# Avaliação 04 Imutabilidade, representação & formato e coesão.

Link do Classroom: <https://classroom.github.com/a/Ihijwdyg>

[strong, firm, focus, always concentrate ... everything is OOP](http://youtu.be/PWiipjG7NEU)

`30%` do curso concluído, [ava-02](5-0.gif), [ava-03](5-1.gif), [ava-04](5-2.gif) 👈 estás aqui.


## Implementar e testar segundo as especificações

- Esta atividade é avaliada com esforço estimado entre 6 e 12h.
- Copie os casos de teste para o método `main` em [App.java](src/App.java), conforme o exemplo que já está no arquivo. Comente com `//` ou `/*` e `*/` as linhas que ainda não foram implementadas.
- Os Casos de Teste podem ser corrigidos se estiverem mal escritos, mas **a especificação dos objetos não pode ser alterada**.
- E, por fim, assegure-se de **assistir as videoaulas antes de começar**, pois lá estão explicados todos os conceitos e práticas presentes nesta atividade.



### Implementar uma classe para gerar objetos imutáveis de Tempo e suas operações básicas

Considere um instante no tempo em horas, minutos e segundos, entre `00:00:00` e `23:59:59`. Implementar construtores e métodos para lidar com esse tempo de maneira *fail-safe* (sem rejeitar as entradas, mas adaptando-as). A API (interface do objeto) será implementada na língua inglesa com construtores para `h:m:s`, `h:m` e somente `h`. Os métodos disponíveis serão `Time#plus(Time):Time`, `Time#plusHours(int):Time`, `Time#plusMinutes(int):Time`, `Time#plusSeconds(int):Time`, `Time#minus(Time):Time`, `Time#minusHours(int):Time`, `Time#minusSeconds(int):Time`, `Time#tick():Time` (adiciona um segundo), `Time#shift():Time` (inverte o turno),`Time#isMidDay():boolean` (questiona se é meio-dia) e `Time#isMidNight():boolean` (questiona se é meia-noite).

Casos de teste:

```java
Time t1 = new Time();
// representação string, padrão 00:00:00
System.out.println(t1.toString().equals("00:00:00"));
Time t2 = new Time(1, 40, 5);
System.out.println(t2.toString().equals("01:40:05"));
Time t3 = t1.plus(t2);
System.out.println(t3.toString().equals("01:40:05"));
System.out.println(t3.hours() == 1);
System.out.println(t3.minutes() == 40);
System.out.println(t3.seconds() == 5);
// deve ser imutável
System.out.println(t1.hours() == 0);
System.out.println(t1.minutes() == 0);
System.out.println(t1.seconds() == 0);
// plus
Time t4 = t3.plus(t2);
System.out.println(t4.toString().equals("03:20:10"));
// implementar equals
System.out.println(t4.equals(new Time(3, 20, 10)));
Time t5 = t2.plusHours(1);
System.out.println(t5.toString().equals("02:40:05"));
Time t6 = t4.plusHours(23);
System.out.println(t6.toString().equals("02:20:10"));
Time t7 = t6.plusMinutes(10);
System.out.println(t7.toString().equals("02:30:10"));
Time t8 = t7.plusSeconds(80);
System.out.println(t8.toString().equals("02:31:30"));
Time t9 = new Time().plusHours(19).plusMinutes(23).plusSeconds(18);
System.out.println(t9.toString().equals("19:23:18"));
Time t10 = t9.plusHours(-1).plusMinutes(-1).plusSeconds(-1);
System.out.println(t10.toString().equals("18:22:17"));
Time t11 = t10.minusHours(2).minusMinutes(2).minusSeconds(2);
System.out.println(t11.toString().equals("16:20:15"));
Time t12 = t11.minusHours(-5);
System.out.println(t12.toString().equals("21:20:15"));
Time t13 = t11.minus(t12);
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
System.out.println(t20.toString().equals("quanto vale t20? escreva aqui"));
```

**Desafio: a classe `Time` com apenas um atributo `int` em vez de três.**



### Representações e Formatos de `Time`

Implementar os métodos de conversão para `Time` conforme os casos de teste.

```java
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
System.out.println(tr4.toLongString().equals("21 horas 15 minutos e 32 segundos"));
System.out.println(Time.fromSeconds(68400).toLongString().equals("19 horas"));
// toDouble
Time tr4 = Time.fromString("16:45:11");
System.out.println(tr4.toDouble()); // 16.75305556 aproximadamente
System.out.println(Time.fromString("13:04:59").toDouble()); // 13.08305556 aproximadamente
double tr5double = Time.fromString("13:04:59").toDouble();
Time tr5 = Time.fromDouble(tr5double);
System.out.println(tr5.toLongString().equals("13 horas 4 minutos e 59 segundos"));
// fromTime
Time tr6 = Time.from(tr5);
// toShortString
System.out.println(tr6.toShortString().equals("13h04m59s"));
System.out.println(Time.fromString("15:03:00").toShortString().equals("15h03m"));
// System.out.println(Time.fromString("05:00:01").toShortString().equals("15h00m01s")); // PATCHED:
System.out.println(Time.fromString("15:00:01").toShortString().equals("15h00m01s"));
// constantes
Time tr7 = Time.MIDDAY;
System.out.println(tr7.toShortString().equals("12h"));
Time tr8 = Time.MIDNIGHT;
System.out.println(tr8.toShortString().equals("00h"));
System.out.println(Time.MIDDAY.toInt() == 43200);
System.out.println(Time.MIDNIGHT.toInt() == 0);
```



### Implementar o objeto `Coordenada`

Instâncias de `Coordenada` devem representar uma posição geográfica no formato de _latitude_ e _longitude_ em graus decimais, sendo que a _latitude_ vai de `-90.0` a `+90.0` e a _longitude_ de `-180.0` a `+180.0`. A construção sem argumentos de uma coordenada deve instanciar _latitude_ `0` e _longitude_ `0`. Após a construção não devem ser permitidas alterações na _latitude_ e _longitude_ a não ser que outra instância seja construída, em outras palavras, os objetos devem ser **imutáveis**.

Considere os Casos de Teste:
```java
// construtores:
Coordenada c1 = new Coordenada();
System.out.println(c1.latitude == 0.0);
System.out.println(c1.longitude == 0.0);

Coordenada c2 = new Coordenada(50.0, 134.0);
System.out.println(c2.latitude == 50.0);
System.out.println(c2.longitude == 134.0);

Coordenada c3 = new Coordenada(-90.0, -180.0);
System.out.println(c3.latitude == -90.0);
System.out.println(c3.longitude == -180.0);

// estas coordenadas são inválidas e devem lançar exceção
// faça serem rejeitadas e depois comente-as para não parar o programa
Coordenada e1 = new Coordenada(-91.0, 0.0);
Coordenada e2 = new Coordenada(100.0, 0.0);
Coordenada e3 = new Coordenada(10.0, -182.0);
Coordenada e4 = new Coordenada(10.0, 200.0);
Coordenada e5 = new Coordenada(-95.0, -200.0);

// imutabilidade: as linhas a seguir devem causar erro de compilação
// verifique se está de acordo e depois comente-as
Coordenada c4 = new Coordenada();
c4.latitude = 30.0;  // não deve permitir reatribuição
c4.longitude = 80.0; // não deve permitir reatribuição

// operações/comandos:
Coordenada in = new Coordenada(30.0, 50.0);
Coordenada out = in.norte(5.0);
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
Coordenada q = new Coordenada();
System.out.println(q.latitude == 0);
System.out.println(q.longitude == 0);
System.out.println(q.noEquador() == true);
System.out.println(q.noMeridiano() == true);
q = q.norte(10.0);
System.out.println(q.latitude == 10);
System.out.println(q.noEquador() == false);
q = q.leste(10.0);
System.out.println(q.noMeridiano() == false);
q = q.leste(170.0);
System.out.println(q.longitude == 180.0);
System.out.println(q.noMeridiano() == true);
q = q.oeste(200.0);
System.out.println(q.longitude == -20.0);
System.out.println(q.noMeridiano() == false);
q = q.oeste(160.0);
System.out.println(q.longitude == -180.0);
System.out.println(q.noMeridiano() == true);

Coordenada r = new Coordenada(30.0, 70.0);
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

// toString:
System.out.println(c1.toString().equals("0.0000000°, 0.0000000°"));
System.out.println(c2.toString().equals("50.0000000°, 134.0000000°"));
System.out.println(c3.toString().equals("-90.0000000°, -180.0000000°"));
System.out.println(out.toString().equals("-50.0000000°, -80.0000000°"));
System.out.println(q.toString().equals("10.0000000°, -180.0000000°"));
System.out.println(r); // -30.0000000°, -70.0000000°
System.out.println(r.toString().equals("-30.0000000°, -70.0000000°"));

Coordenada t = new Coordenada(-32.0714021, -52.1425059);
System.out.println(t); // -32.0714021°, -52.1425059°
System.out.println(t.toString().equals("-32.0714021°, -52.1425059°"));

// equals: precisa considerar a imprecisão do double
System.out.println(t.equals(r) == false);
Coordenada y = new Coordenada(-32.0714021, -52.1425059);
System.out.println(t.equals(y) == true);
System.out.println(y.equals(t) == true);
System.out.println(y.equals(y) == true);
System.out.println(y.equals(r) == false);
```

**Informações adicionais:**

- Imagem esclarecedora <http://eogn.com/images/newsletter/2014/Latitude-and-longitude.png>
- Sobre coordenadas decimais <http://opussig.blogspot.com.br/2013/01/coordenadas-geograficas-em-formato.html>
- Sessão _remember_ <http://brasilescola.uol.com.br/geografia/coordenadas-geograficas.htm>



### Implementar a ideia de _Tempo Decorrido_

Em português _"time span"_ seria **intervalo de tempo**. Assim, ele pode representar um tempo em dias, horas, minutos e segundos. `TimeSpan` deve ser imutável.

Considere os Casos de Teste:

```java
// construtores
TimeSpan ts1 = new TimeSpan(7, 3, 45, 35);
System.out.println(ts1.getDays() == 7);
System.out.println(ts1.getHours() == 3);
System.out.println(ts1.getMinutes() == 45);
System.out.println(ts1.getSeconds() == 35);
ts1 = new TimeSpan(8, 12, 9);
System.out.println(ts1.getDays() == 0);
System.out.println(ts1.getHours() == 8);
System.out.println(ts1.getMinutes() == 12);
System.out.println(ts1.getSeconds() == 9);
ts1 = new TimeSpan(4, 18, 110); // entradas adaptáveis
System.out.println(ts1.getDays() == 0);
System.out.println(ts1.getHours() == 4);
System.out.println(ts1.getMinutes() == 19);
System.out.println(ts1.getSeconds() == 50);
ts1 = new TimeSpan(4, 68, 110); // entradas adaptáveis
System.out.println(ts1.getDays() == 0);
System.out.println(ts1.getHours() == 5);
System.out.println(ts1.getMinutes() == 9);
System.out.println(ts1.getSeconds() == 50);

// validade e exceções
try {
  ts1 = new TimeSpan(-1, 4, 68, 110);
  // se essa linha for impressa uma exceção não foi lançada
  // falhando no caso de teste
  System.out.println(false);
} catch (IllegalArgumentException e) {
  System.out.println(true); // se for impresso a exceção foi lançada! ok!
  System.out.println(e.getMessage()); // Can't be negative
}
try {
  ts1 = new TimeSpan(1, -4, 68, 110); System.out.println(false);
} catch (IllegalArgumentException e) {
  System.out.println(true);
}
try {
  ts1 = new TimeSpan(1, 4, -68, 110); System.out.println(false);
} catch (IllegalArgumentException e) {
  System.out.println(true);
}
try {
  ts1 = new TimeSpan(1, 4, 68, -110); System.out.println(false);
} catch (IllegalArgumentException e) {
  System.out.println(true);
}
try {
  ts1 = new TimeSpan(0, 0, 0, 0); System.out.println(false);
} catch (IllegalArgumentException e) {
  System.out.println(e.getMessage()); // Can't be zero
  System.out.println(true);
}

// toString
System.out.println(ts1); // deve imprimir 5 hours, 9 minutes e 50 seconds
System.out.println(ts1.toString().equals("5 hours, 9 minutes e 50 seconds"));
TimeSpan ts2 = new TimeSpan(1, 12, 45, 1);
System.out.println(ts2.toString().equals("1 day, 12 hours, 45 minutes e 1 second"));
TimeSpan ts3 = new TimeSpan(0, 0, 0, 15);
System.out.println(ts3.toString().equals("15 seconds"));
ts3 = new TimeSpan(0, 1, 0, 0);
System.out.println(ts3.toString().equals("1 hour"));
ts3 = new TimeSpan(0, 0, 25, 0);
System.out.println(ts3.toString().equals("25 minutes"));
// toString em português
System.out.println(ts1.toString("pt").equals("5 horas, 9 minutos e 50 segundos"));
System.out.println(ts2.toString("pt")); //1 dia, 12 horas, 45 minutos e 1 segundo
System.out.println(ts2.toString("pt").equals("1 dia, 12 horas, 45 minutos e 1 segundo"));
System.out.println(ts3.toString("pt").equals("25 minutos"));

// equals, greaterThan, lessThan (igual, maior que, menor que)
TimeSpan ts4 = new TimeSpan(1, 12, 45, 1);
System.out.println(ts2.equals(ts4) == true);
System.out.println(ts4.equals(ts2) == true);
System.out.println(ts4.equals(ts3) == false);
System.out.println(ts4.greaterThan(ts3) == true);
System.out.println(ts4.lessThan(ts3) == false);
System.out.println(ts3.lessThan(ts2) == true);
System.out.println(ts4.lessThan(ts2) == false);
System.out.println(ts4.greaterThan(ts2) == false);
```



### Ponto

Considere um sistema para bater ponto. Nessa fase de desenvolvimento ele não é muito complexo, basta informar o nome do funcionário para abrir um ponto e em seguida "bater" o ponto para registrar entrada e saída.

Garanta que a classe `Ponto` tenha alta coesão. Portanto, fique a vontade para delegar operações para outras classes/objetos, inclusive adicionando métodos novos --- desde que não quebre testes anteriores, claro.

```java
// Spock é um Funcionário
// Ponto representa uma presença do funcionário
// Ponto é mutável, pois representa um processo ao longo do tempo!!!
Ponto ponto = new Ponto("Spock");
// toString
System.out.println(ponto); // Spock não bateu ponto
// Spock bateu ponto às 07:50:15
ponto.bater("07:50:15");
System.out.println(ponto); // Spock entrou às 07h50m15s
System.out.println(ponto.toString().equals("Spock entrou às 07h50m15s")); //
ponto.bater("12:02:10");
System.out.println(ponto); // Spock entrou às 07h50m15s e saiu às 12h02m10s e permaneceu 4 horas, 2 minutos e 10 segundos
// System.out.println(ponto.toString().equals("Spock entrou às 07h50m15s e saiu às 12h02m10s e permaneceu 4 horas, 2 minutos e 10 segundos"));
// PATCH:
System.out.println(ponto.toString().equals("Spock entrou às 07h50m15s e saiu às 12h02m10s e permaneceu 4 horas, 11 minutos e 55 segundos"));

Ponto ponto2 = new Ponto("Kirk");
ponto2.bater("14:00:00");
System.out.println(ponto2); // Kirk entrou às 14h
System.out.println(ponto2.toString().equals("Kirk entrou às 14h"));
ponto2.bater("03:10:00");
System.out.println(ponto2); // Kirk entrou às 14h e saiu às 03h10m e permaneceu 13 horas e 10 minutos
System.out.println(ponto2.toString().equals("Kirk entrou às 14h e saiu às 03h10m e permaneceu 13 horas e 10 minutos"));

// não deve ser possível bater o ponto fechado
try {
  ponto2.bater("04:15:08");
  System.out.println(false); // falhou
} catch (IllegalStateException e) {
  System.out.println(e.getMessage()); // Entrada e saída já foram batidas e o ponto está fechado
  System.out.println(true); // ok, passou!
}
```

* * *

> There are known knowns. These are things we know that we know.
> There are known unknowns. That is to say, there are things that we know we don't know.
> But there are also unknown unknowns. There are things we don't know we don't know.
>
> -- **Donald Rumsfeld**
