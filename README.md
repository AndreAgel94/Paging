# BecaMobile2020

## Conheça mais sobre os personagens da Marvel, procure ou pesquise por um personagem e saiba sua história.

![beca_mobile](https://github.com/AndreAgel94/BecaMobile2020/blob/dev/GIF%20Beca.gif)

### Projeto
Desenvolvido para botar na prática os conhecimentos adquiridos na Beca Mobile Everis. 
Utilizando a [API da Marvel](https://developer.marvel.com/) para listar os personagens para que o usuário
possa escolher um do seu interesse, ou pesquisar por um específico, apresentando uma descrição da história 
do personagem  
### Dependências 
* [Retrofit](https://square.github.io/retrofit/)
A type-safe HTTP client for Android and Java
* [Moshi](https://github.com/square/moshi/)
modern JSON library for Android and Java. It makes it easy to parse JSON into Java objects
* [Picasso](https://square.github.io/picasso/#download)
A powerful image downloading and caching library for Android
* [Recycler View](https://developer.android.com/guide/topics/ui/layout/recyclerview)
Create a List with RecyclerView
* [MaterialSearchView](https://github.com/MiguelCatalan/MaterialSearchView)
Cute library to implement SearchView in a Material Design Approach.
### Como executar?
* Clone o repositório <br />
```
$ git clone https://github.com/AndreAgel94/BecaMobile2020.git
```
* instale o [Android Studio](https://developer.android.com/studio/?gclid=Cj0KCQjwqfz6BRD8ARIsAIXQCf1TDuk2Rpr7UFi3W57PvX4WS4dVyMI8QK0uaK-zYwq1T-TweWTCuvYaAhm8EALw_wcB&gclsrc=aw.ds)
* Abra o projeto pelo Android Studio
* dê permissão de acesso a internet ao projeto, no seu manifest adcione: <br />

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```
* Substitua as constantes do arquivo Constants.kt para as suas Chaves de API

```
import java.util.*

const val PUBLIC_KEY = "YOUR PUBLIC KEY HERE"
const val PRIVATE_KEY = "YOUR PRIVATE KEY HERE"
val TS = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()
val HASH = "$TS$PRIVATE_KEY$PUBLIC_KEY"
```

* Em seu gradle(Module: app) implemente as depêndencias e sincronize(Sync Now)
* Execute em um emulador <br /> 
ou [no seu aparelho](https://developer.android.com/training/basics/firstapp/running-app?hl=pt-br)
