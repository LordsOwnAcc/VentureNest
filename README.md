# VentureNest

An Android app for a startup incubation space. It gives founders, mentors, and visitors a single place to explore the incubator, the startups inside it, programme analytics, upcoming events, and success stories, with an AI chatbot to answer questions on demand.



## Features

- **Incubator info**: about the space, programmes, and facilities
- **Startup directory**: details for each incubated startup (`TODO`: fields, e.g. sector, founders, stage)
- **Analytics**: incubation metrics and startup insights (`TODO`: charts/KPIs shown)
- **Events**: upcoming and past incubator events
- **Success stories**: showcase of graduated and thriving startups
- **AI chatbot**: ask questions about the incubator, startups, and events
- REST API integration via Retrofit

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| Platform | Android (Gradle Kotlin DSL) |
| Networking | Retrofit + OkHttp |
| JSON |  Gson 
| AI Chatbot |  Gemini 
| UI | Jetpack Compose 
| Architecture | MVVM 

## Screenshots

## App Screenshots

| Home | Startups | Analytics | Chatbot |
|:---:|:---:|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/a7d19ffe-bea0-4bab-b84c-bc05f4bfdb22" width="200" alt="Home Screen" /> | <img src="https://github.com/user-attachments/assets/535fb019-e55d-49c6-b3d9-c1ac4dfbce50" width="200" alt="Startups Screen" /> | <img src="https://github.com/user-attachments/assets/cbe253df-9fda-4ee2-bc8c-7c3c16c41431" width="200" alt="Analytics Screen" /> | <img src="https://github.com/user-attachments/assets/a32e843d-ce41-428d-8d96-e86d9ca4d4f3" width="200" alt="Chatbot Screen" /> |

## Architecture

```
app/src/main/java/<package>/
├── data/        # Retrofit service, API models, repositories
├── ui/          # Screens: home, startups, analytics, events, stories, chatbot
└── util/
```

Request flow for the chatbot:

```
UI -> ViewModel -> Repository -> Retrofit API -> AI service -> response -> UI
```

## Getting Started

### Prerequisites

- Android Studio (latest stable)
- JDK 17
- Android SDK, min SDK 35

### Setup

```bash
git clone https://github.com/LordsOwnAcc/VentureNest.git
cd VentureNest
```

1. Open the project in Android Studio and let Gradle sync.
2. Add your keys to `local.properties` (this file is git-ignored):

   ```properties
   BASE_URL=https://your-api-url/
   AI_API_KEY=your_key_here
   ```

3. Run on an emulator or device (`Shift + F10`), or from the terminal:

   ```bash
   ./gradlew assembleDebug
   ```

> Never commit API keys. If one was ever pushed, rotate it.

## Retrofit Usage

```kotlin
interface ApiService {
    @GET("startups")
    suspend fun getStartups(): List<Startup>

    @GET("events")
    suspend fun getEvents(): List<Event>

    @POST("chat")
    suspend fun sendMessage(@Body request: ChatRequest): ChatResponse
}

val retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiService::class.java)
```


## Roadmap

- [ ] Fixing ui and testing for pre-deployment.
- [ ] Deployment on PlayStore

## Contributing

1. Fork the repo
2. Create a branch: `git checkout -b feature/your-feature`
3. Commit and push
4. Open a pull request

## Author

**Sumit** - [@LordsOwnAcc](https://github.com/LordsOwnAcc)
