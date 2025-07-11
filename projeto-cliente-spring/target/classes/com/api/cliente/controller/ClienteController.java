Źžŗ¾   7   ,com/api/cliente/controller/ClienteController  java/lang/Object clienteService (Lcom/api/cliente/service/ClienteService; RuntimeVisibleAnnotations 8Lorg/springframework/beans/factory/annotation/Autowired; <init> ()V Code
   	 
 LineNumberTable LocalVariableTable this .Lcom/api/cliente/controller/ClienteController; listarTodos +()Lorg/springframework/http/ResponseEntity; 	Signature ^()Lorg/springframework/http/ResponseEntity<Ljava/util/List<Lcom/api/cliente/model/Cliente;>;>; 4Lorg/springframework/web/bind/annotation/GetMapping;	    
    &com/api/cliente/service/ClienteService   ()Ljava/util/List;
  !   'org/springframework/http/ResponseEntity " # ok =(Ljava/lang/Object;)Lorg/springframework/http/ResponseEntity; clientes Ljava/util/List; LocalVariableTypeTable 1Ljava/util/List<Lcom/api/cliente/model/Cliente;>; buscarPorId ;(Ljava/lang/Long;)Lorg/springframework/http/ResponseEntity; \(Ljava/lang/Long;)Lorg/springframework/http/ResponseEntity<Lcom/api/cliente/model/Cliente;>; value /{id} "RuntimeVisibleParameterAnnotations 6Lorg/springframework/web/bind/annotation/PathVariable;
  0 ( 1 &(Ljava/lang/Long;)Ljava/util/Optional;
 3 5 4 java/util/Optional 6 7 	isPresent ()Z
 3 9 : ; get ()Ljava/lang/Object; = com/api/cliente/model/Cliente
  ? @ A notFound :()Lorg/springframework/http/ResponseEntity$HeadersBuilder; C E D 6org/springframework/http/ResponseEntity$HeadersBuilder F  build id Ljava/lang/Long; cliente Ljava/util/Optional; 5Ljava/util/Optional<Lcom/api/cliente/model/Cliente;>; StackMapTable MethodParameters 	cadastrar K(Lcom/api/cliente/dto/ClienteDTO;)Lorg/springframework/http/ResponseEntity; l(Lcom/api/cliente/dto/ClienteDTO;)Lorg/springframework/http/ResponseEntity<Lcom/api/cliente/model/Cliente;>; 5Lorg/springframework/web/bind/annotation/PostMapping; 5Lorg/springframework/web/bind/annotation/RequestBody;
 < 
 U W V com/api/cliente/dto/ClienteDTO X Y getNome ()Ljava/lang/String;
 < [ \ ] setNome (Ljava/lang/String;)V
 U _ ` Y getEmail
 < b c ] setEmail
 U e f Y getSenha
 < h i ] setSenha
  k l m salvar @(Lcom/api/cliente/model/Cliente;)Lcom/api/cliente/model/Cliente;	 o q p #org/springframework/http/HttpStatus r s CREATED %Lorg/springframework/http/HttpStatus;
  u v w status \(Lorg/springframework/http/HttpStatus;)Lorg/springframework/http/ResponseEntity$BodyBuilder; y { z 3org/springframework/http/ResponseEntity$BodyBuilder | # body 
clienteDTO  Lcom/api/cliente/dto/ClienteDTO; Lcom/api/cliente/model/Cliente; novoCliente 	atualizar [(Ljava/lang/Long;Lcom/api/cliente/dto/ClienteDTO;)Lorg/springframework/http/ResponseEntity; |(Ljava/lang/Long;Lcom/api/cliente/dto/ClienteDTO;)Lorg/springframework/http/ResponseEntity<Lcom/api/cliente/model/Cliente;>; 4Lorg/springframework/web/bind/annotation/PutMapping;
     existePorId (Ljava/lang/Long;)Z clienteExistente clienteSalvo deletar M(Ljava/lang/Long;)Lorg/springframework/http/ResponseEntity<Ljava/lang/Void;>; 7Lorg/springframework/web/bind/annotation/DeleteMapping;
     (Ljava/lang/Long;)V
    A 	noContent 
SourceFile ClienteController.java 8Lorg/springframework/web/bind/annotation/RestController; 8Lorg/springframework/web/bind/annotation/RequestMapping; /api/clientes InnerClasses BodyBuilder HeadersBuilder !                    	 
     /     *· ±                                            W     *“ ¶ L+ø °       
                      $ %  &       $ '   ( )      *        +[ s , -     .           $*“ +¶ /M,¶ 2 ,¶ 8Ą <ø °ø >¹ B °           % 	 '  (  *         $       $ G H  	  I J  &     	  I K  L    ü  3 M    G    N O      P      Q   -     R           6» <Y· SM,+¶ T¶ Z,+¶ ^¶ a,+¶ d¶ g*“ ,¶ jN² nø t-¹ x °           0  1  2  3   5 ) 6    *    6       6 } ~   . I   )     M    }                   +[ s , -     .    R          b*“ +¶  ø >¹ B °*“ +¶ /N-¶ 2 8-¶ 8Ą <:,¶ T¶ Z,¶ ^¶ a,¶ d¶ g*“ ¶ j:ø °ø >¹ B °       .    ;  <  ?  A $ B - C 6 D ? E H G S H Y J    >    b       b G H    b } ~   E  J  - , I   S     &      E  K  L   	 ü D 3 M   	 G   }     )              +[ s , -     .      n     %*“ +¶  ø >¹ B °*“ +¶ ø ¹ B °           P  Q  T  U        %       % G H  L     M    G                   +[ s       y  	 C  	