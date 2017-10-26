# Stetho

### What is Stetho ?
Stetho: A debugging bridge for Android applications. It is an Open Source debug library developed by the Facebook team. When enabled developers gain access to the Chrome Developers Tools, thus it is possible to have a rich, interactive debugging experience.

### How to access ?
+ Open Google Chrome
+ Type: [**chrome://inspect**](chrome://inspect)
+ Launch the app on the emulator
+ The option for inspecting will appear in Google Chrome page



### How to create ?
#### Required items:
1. Add Gradle Dependencies
2. Create App (Application) and initialise Stetho
3. Create a Retrofit Client and add Stetho as interceptor
4. Create any place for Network Calls

#### How to do them ?

build.gradle (App level)
```java
ext {
    retrofit2Version = '2.1.0'
    retrofit2GsonConverterVersion = '2.1.0'
    stethoVersion = '1.5.0'
}

dependencies {
    compile "com.squareup.retrofit2:retrofit:$retrofit2Version"
    compile "com.squareup.retrofit2:converter-gson:$retrofit2GsonConverterVersion"
    
    compile "com.facebook.stetho:stetho:$stethoVersion"
    compile "com.facebook.stetho:stetho-okhttp3:$stethoVersion"
}
```


App.java (Application file)
```java

@Override
public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG)
        initStetho();
}

private void initStetho() {
    Stetho.Initializer initializer = Stetho.newInitializerBuilder(this)
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
            .build();
    Stetho.initialize(initializer);
}
```


RetrofitClient.java
```java
private static Retrofit retrofit = null;

static Retrofit getClient() {

    if (retrofit == null) {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpWithStetho())
                .baseUrl(BASE_URL)
                .build();
    }

    return retrofit;
}

private static OkHttpClient getOkHttpWithStetho() {

    return new OkHttpClient.Builder()
            .addNetworkInterceptor(new StethoInterceptor())
            .build();
}
```

MainActivity.java
```java
networkManager.getApi().yourNetworkCall().enqueue(new Callback<Object>(){
    ...
    // Implemented Methods
    ...
});
```


### Note:
In Application file: 
1. Add INTERNET permission
2. Add app name (App.java)


### Help Used
Dummy REST API Json fetcher:
[https://jsonplaceholder.typicode.com/](https://jsonplaceholder.typicode.com/)