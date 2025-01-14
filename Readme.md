# Apoio360
**Curso:** Licenciatura em Engenharia de Sistemas Informáticos, 3.º Ano  
**Unidade Curricular:** Projeto Aplicado  
**Docente:** Professora Patrícia Leite e Nuno Mendes  
**Ano Letivo:** 2024/2025  

## Constituição do Grupo  
| Número  | Nome           | Email                  |
| :---    | :---           | :---                  |
| 22999   | Joana Pimenta  | a22999@alunos.ipca.pt |
| 23129   | José Pinheiro  | a23129@alunos.ipca.pt |
| 25591   | Daniela Brito  | a25591@alunos.ipca.pt |
| 26536   | Rodrigo Pilar  | a26536@alunos.ipca.pt |

## Introdução
A aplicação **Apoio360** moderniza a gestão de uma Loja Social, facilitando o apoio a famílias em situação de vulnerabilidade. Desenvolvida em **Kotlin** e utilizando **Firebase** para gestão de dados, a aplicação substitui processos manuais por uma solução digital. 

Com funcionalidades como registo de beneficiários, gestão de doações e organização de turnos, o **Apoio360** garante eficiência e acessibilidade, atendendo às necessidades de voluntários e gestores.

### Tecnologias Utilizadas
- **Kotlin:** Linguagem de programação principal (versão 1.6.21).
- **Firebase:** Base de dados em tempo real, autenticação e armazenamento (SDK 31.0.1).
- **Room:** Armazenamento local para dados offline (versão 2.4.2).
- **Retrofit:** Comunicação com APIs externas (versão 2.9.0).
- **Android Studio:** IDE de desenvolvimento (versão Flamingo 2022.1.1).

## Funcionalidades Principais
- **Gestão de Beneficiários:** Registo e consulta de dados.
- **Check-in e Check-out:** Fluxo simplificado de entrada e saída de beneficiários.
- **Gestão de Doações:** Registo e organização de doações, com anexação de imagens.
- **Escalas de Voluntários:** Planeamento e gestão de turnos.
- **Relatórios:** Geração de relatórios para suporte à gestão.
- **Segurança:** Diferentes níveis de acesso para voluntários e gestores.

---
## Processo de Atendimento na Loja Social com 'Apoio360'

#### 1. **Antes de Abrir a Loja**

O colaborador da Loja Social chega ao local para preparar a abertura. O primeiro passo é autenticar-se na aplicação **Apoio360**. Para isso, abre a aplicação no dispositivo móvel e é recebido pelo ecrã de **Login**.

- **O que acontece na aplicação?**
  - O ficheiro **`MainActivity.kt`** é iniciado. Este ficheiro carrega o sistema de navegação principal da aplicação, configurando o **`NavHost`**, que define o ecrã inicial como o **`LoginScreen`**.
  - O **`LoginScreen`** verifica se o utilizador já está autenticado através do **Firebase Authentication**.
    - Caso já exista uma sessão válida, o utilizador é redirecionado automaticamente para o **HomeScreen**.
    - Caso contrário, é necessário introduzir as credenciais (email e password).
  - Após introduzir as credenciais, o sistema autentica o utilizador através do Firebase. Se for bem-sucedido:
    - O utilizador é redirecionado para o **HomeScreen**.
    - O ficheiro **`FirebaseService.kt`** inicializa, garantindo que os dados em tempo real da loja (como capacidade atual e doações) estão sincronizados com a base de dados.

#### 2. **Chegada do Beneficiário**

Quando a loja abre, um beneficiário aproxima-se para realizar um check-in. O colaborador abre a aplicação e navega para o ecrã de **CheckIn**.

- **O que acontece na aplicação?**
  - O ficheiro **`CheckInScreen.kt`** é carregado. Este ecrã permite pesquisar pelo nome ou número de telefone do beneficiário.
  - Ao introduzir os dados, o sistema comunica com o **`BeneficiaryRepositoryImpl.kt`**. Este repositório:
    - Consulta os dados locais através do **RoomDatabase** (via **`BeneficiaryDao`**).
    - Sincroniza a informação com o Firebase Realtime Database para garantir que os dados estão atualizados.
  - Se o beneficiário for encontrado:
    - Os detalhes do beneficiário são exibidos, como nome, histórico de visitas e estado atual (por exemplo, se está autorizado a receber doações).
    - O colaborador confirma a entrada no sistema, e o ficheiro **`FirebaseService.kt`** atualiza o estado no Firebase (como a lotação atual da loja).
  - Se o beneficiário não for encontrado:
    - O colaborador pode registar um novo beneficiário diretamente no sistema, utilizando o mesmo ecrã. Este registo é gerido pelo **`BeneficiaryRepositoryImpl.kt`**, que atualiza o Firebase Realtime Database e o **RoomDatabase**.


#### 3. **Distribuição de Doações**

O beneficiário desloca-se ao espaço da loja para escolher os itens de que necessita. Após selecionar os produtos, o colaborador utiliza o ecrã de **Doações** para registar o que foi entregue.

- **O que acontece na aplicação?**
  - O colaborador navega para o **`DonationScreen.kt`**, que permite registar as doações.
  - O colaborador introduz os itens entregues (por exemplo, roupa ou utensílios domésticos) e pode adicionar imagens dos produtos através do sistema de anexos.
  - O ficheiro **`DonationRepositoryImpl.kt`** gere este registo:
    - Os detalhes da doação são enviados para o Firebase Realtime Database.
    - As imagens associadas são carregadas para o Firebase Storage, com os respetivos URLs armazenados no Firebase Realtime Database.
  - Após a submissão, o sistema atualiza o histórico de doações do beneficiário, acessível no **`BeneficiaryListScreen.kt`**.


#### 4. **Encerramento da Loja**

No final do dia, o colaborador organiza os dados e verifica os relatórios no ecrã de **Relatórios**.

- **O que acontece na aplicação?**
  - No **`HomeScreen`**, o colaborador seleciona a opção de relatórios, que carrega o **`ReportScreen.kt`**.
  - O ficheiro **`ReportRepositoryImpl.kt`** gere a geração de relatórios diários, consolidando dados de:
    - Beneficiários atendidos.
    - Itens doados.
    - Disponibilidade de voluntários e escalas futuras.
  - Estes relatórios são exibidos em tempo real, com gráficos ou tabelas, gerados a partir dos dados armazenados no Firebase Realtime Database.


---

## Estrutura de Pastas e Ficheiros
```plaintext
app/
├── src/
│    └── main/
│       └── java/com/example/LojaSocial/
│           ├── MainActivity.kt (Classe principal que configura a navegação e inicializa dependências como Firebase)
│           ├── data/
│           │   ├── local/
│           │   │   ├── RoomDatabase.kt (Configura a base de dados local Room e regista tabelas)
│           │   │   └── dao/
│           │   │       └── BeneficiaryDao.kt (Interface para aceder e manipular dados de beneficiários na base de dados local)
│           │   ├── remote/
│           │   │   ├── api/
│           │   │   │   └── FirebaseService.kt (Implementa serviços de interação com o Firebase, como atualizações em tempo real)
│           │   │   ├── model/
│           │   │       ├── Beneficiary.kt (Modelo de dados para representar beneficiários)
│           │   │       ├── Donation.kt (Modelo de dados para representar doações)
│           │   │       └── Schedule.kt (Modelo de dados para representar escalas de voluntários)
│           │   └── repository/
│           │       ├── BeneficiaryRepositoryImpl.kt (Repositório que implementa a lógica para aceder e sincronizar dados de beneficiários com o Firebase e Room)
│           │       ├── DonationRepositoryImpl.kt (Repositório que implementa a lógica para registo e armazenamento de doações)
│           │       └── ScheduleRepositoryImpl.kt (Repositório que implementa a lógica para criar e gerir escalas de voluntários)
│           │   
│           ├── domain/
│           │   ├── model/
│           │   │   ├── BeneficiaryDomain.kt (Modelo de domínio que encapsula dados do beneficiário para lógica de negócio)
│           │   │   ├── DonationDomain.kt (Modelo de domínio que encapsula dados de doações para lógica de negócio)
│           │   │   └── ScheduleDomain.kt (Modelo de domínio que encapsula dados de escalas para lógica de negócio)
│           │   └── use_case/
│           │       ├── GetBeneficiaryUseCase.kt (Caso de uso que coordena a obtenção de dados de beneficiários)
│           │       ├── RegisterDonationUseCase.kt (Caso de uso que coordena o registo de doações)
│           │       └── OrganizeScheduleUseCase.kt (Caso de uso que coordena a organização de escalas de voluntários)
│           │
│           ├── presentation/
│           │   ├── beneficiary/
│           │   │   ├── BeneficiaryListScreen.kt (Ecrã que exibe a lista de beneficiários e permite interação com os dados)
│           │   │   └── BeneficiaryViewModel.kt (ViewModel que gere a lógica para o ecrã de lista de beneficiários)
│           │   ├── check_in/
│           │   │   ├── CheckInScreen.kt (Ecrã para registar check-ins e check-outs de beneficiários)
│           │   │   └── CheckInViewModel.kt (ViewModel que gere a lógica para o ecrã de check-in)
│           │   ├── donation/
│           │   │   ├── DonationScreen.kt (Ecrã para registar novas doações e visualizar informações relacionadas)
│           │   │   └── DonationViewModel.kt (ViewModel que gere a lógica para o ecrã de doações)
│           │   └── schedule/
│           │       ├── CalendarScreen.kt (Ecrã para voluntários registarem a sua disponibilidade)
│           │       ├── ScheduleScreen.kt (Ecrã para gestores organizarem turnos de voluntários)
│           │       └── ScheduleViewModel.kt (ViewModel que gere a lógica para o ecrã de organização de escalas)
│           │
│           ├── utils/
│           │   └── Extensions.kt (Funções utilitárias e extensões utilizadas em várias partes da aplicação)
│           │
│           └── di/
│               ├── FirebaseModule.kt (Configura as dependências necessárias para integrar o Firebase)
│               ├── RoomModule.kt (Configura as dependências necessárias para a base de dados Room)
│               └── RepositoryModule.kt (Configura as dependências necessárias para os repositórios de dados)
│    
└── build.gradle.kts (Ficheiro de configuração do Gradle para o projeto)
```

---

## Fluxo de Dados

### 1. **Início da Aplicação**
- O ficheiro **`MainActivity.kt`** serve como ponto de entrada da aplicação. Ele configura a navegação principal, inicializando o `NavHost`, que gerencia os diferentes ecrãs da aplicação. A configuração inicial é feita utilizando o Jetpack Navigation, onde as rotas para cada ecrã são definidas no ficheiro:
  
    ```kotlin
    @Composable
    fun MainActivityNavHost(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = "loginScreen"
        ) {
            composable("loginScreen") { LoginScreen(navController) }
            composable("homeScreen") { HomeScreen(navController) }
            composable("checkInScreen") { CheckInScreen(navController) }
        }
    }
    ```

- A navegação é gerida pelo objeto `NavController`, que permite transitar entre ecrãs chamando métodos como `navigate()` para direcionar o utilizador ao próximo ecrã.

    ```kotlin
    navController.navigate("homeScreen")
    ```

- Além disso, **`MainActivity.kt`** utiliza o `setContent` para definir o layout principal, aplicando o tema da aplicação através de `LojaSocialTheme`.

    ```kotlin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LojaSocialTheme {
                val navController = rememberNavController()
                MainActivityNavHost(navController)
            }
        }
    }
    ```

- Este ficheiro também é responsável por inicializar as dependências, como o Firebase, garantindo que todos os serviços estejam disponíveis ao iniciar a aplicação:

    ```kotlin
    FirebaseApp.initializeApp(this)
    ```

### 2. **Autenticação**
- Utilizadores autenticam-se através do Firebase Authentication. Após o login, são redirecionados para o respetivo ecrã, consoante o seu perfil (voluntário ou gestor).

### 3. **Gestão de Beneficiários**
- O ecrã **`BeneficiaryListScreen.kt`** permite aos gestores aceder à lista de beneficiários.
- O **`BeneficiaryViewModel.kt`** gere a interação entre a interface e os dados através da comunicação com o repositório **`BeneficiaryRepositoryImpl.kt`**, responsável por obter, atualizar e armazenar informações sobre os beneficiários.
  - Para obter os dados, o método **`getAllBeneficiaries`** no repositório é chamado:
    ```kotlin
    fun getAllBeneficiaries(): LiveData<List<Beneficiary>> {
        return Transformations.map(beneficiaryDao.getAll()) { entities ->
            entities.map { it.toDomainModel() }
        }
    }
    ```
    Este método converte entidades da base de dados local Room em modelos de domínio utilizáveis na interface.
  
  - O **`BeneficiaryRepositoryImpl.kt`** também interage com o Firebase Realtime Database para sincronizar dados:
    ```kotlin
    fun syncBeneficiaries() {
        val databaseRef = FirebaseDatabase.getInstance().getReference("beneficiaries")
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val beneficiaries = snapshot.children.mapNotNull { it.getValue(Beneficiary::class.java) }
                beneficiaryDao.insertAll(beneficiaries.map { it.toEntity() })
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", error.message)
            }
        })
    }
    ```
    Este processo assegura que os dados armazenados localmente estão sempre atualizados com a base de dados remota.

  - O **`BeneficiaryViewModel.kt`** utiliza o método **`syncBeneficiaries`** ao iniciar a aplicação para garantir que a lista apresentada está atualizada:
    ```kotlin
    init {
        viewModelScope.launch {
            repository.syncBeneficiaries()
        }
    }
    ```

  - Os dados são expostos para a interface através de um **`LiveData`**:
    ```kotlin
    val beneficiaries: LiveData<List<Beneficiary>> = repository.getAllBeneficiaries()
    ```

### 4. **Check-in e Check-out**
- No ecrã **`CheckInScreen.kt`**, o utilizador pesquisa o beneficiário pelo nome ou telefone.
- Para sincronizar o estado da lotação em tempo real, o Firebase Realtime Database é utilizado. 
  - A função **`updateCapacity`** no ficheiro **`FirebaseService.kt`** atualiza o nó correspondente no Realtime Database quando ocorre uma alteração, como o registo de um novo check-in ou check-out.
  - Por exemplo:
    ```kotlin
    fun updateCapacity(currentCapacity: Int) {
        val databaseRef = FirebaseDatabase.getInstance().getReference("capacity")
        databaseRef.setValue(currentCapacity)
    }
    ```
  - O ecrã **`CheckInScreen.kt`** utiliza um Listener para observar alterações no nó "capacity" do Firebase e atualizar dinamicamente a interface:
    ```kotlin
    val databaseRef = FirebaseDatabase.getInstance().getReference("capacity")
    databaseRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val capacity = snapshot.getValue(Int::class.java) ?: 0
            updateCapacityIndicator(capacity)
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("FirebaseError", error.message)
        }
    })
    ```
- O check-out regista a saída do beneficiário e atualiza o histórico na base de dados Firebase através do método **`registerCheckOut`** no ficheiro **`BeneficiaryRepositoryImpl.kt`**:
    ```kotlin
    fun registerCheckOut(beneficiaryId: String) {
        val checkOutRef = FirebaseDatabase.getInstance().getReference("checkOuts")
        checkOutRef.child(beneficiaryId).setValue(ServerValue.TIMESTAMP)
    }
    ```

### 5. **Gestão de Doações**
- O ficheiro **`DonationScreen.kt`** permite o registo de doações com anexação de imagens.
- As informações são enviadas ao Firebase via **`DonationRepositoryImpl.kt`**.
  - A classe **`DonationRepositoryImpl.kt`** utiliza o Firebase Realtime Database para armazenar detalhes das doações. Por exemplo:
    ```kotlin
    fun registerDonation(donation: Donation) {
        val databaseRef = FirebaseDatabase.getInstance().getReference("donations")
        val donationId = databaseRef.push().key
        donationId?.let {
            databaseRef.child(it).setValue(donation)
        }
    }
    ```
  - Cada doação é representada por uma instância da classe **`Donation`**, contendo informações como:
    ```kotlin
    data class Donation(
        val donorName: String?,
        val type: String,
        val date: String,
        val imageUrl: String?
    )
    ```
  - A interação com o Firebase inclui a anexação de imagens, utilizando o Firebase Storage:
    ```kotlin
    fun uploadImage(imageUri: Uri, callback: (String?) -> Unit) {
        val storageRef = FirebaseStorage.getInstance().reference.child("donationImages/${UUID.randomUUID()}")
        storageRef.putFile(imageUri)
            .addOnSuccessListener { taskSnapshot ->
                taskSnapshot.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                    callback(it.toString())
                }
            }
            .addOnFailureListener {
                callback(null)
            }
    }
    ```
  - Assim que a imagem é carregada, o URL é associado à doação no Firebase Realtime Database.

### 6. **Escalas de Voluntários**
- Voluntários registam a sua disponibilidade no ecrã **`CalendarScreen.kt`**.
  - Cada voluntário preenche um formulário indicando os dias e horários em que está disponível. Os dados são enviados ao Firebase através do método **`registerAvailability`**:
    ```kotlin
    fun registerAvailability(volunteerId: String, availability: Availability) {
        val databaseRef = FirebaseDatabase.getInstance().getReference("volunteerAvailability")
        databaseRef.child(volunteerId).setValue(availability)
    }
    ```
  - A classe **`Availability`** é usada para representar a disponibilidade:
    ```kotlin
    data class Availability(
        val days: List<String>,
        val times: List<String>
    )
    ```
- Gestores organizam os turnos no **`ScheduleScreen.kt`**.
  - O gestor consulta as disponibilidades dos voluntários no Firebase e utiliza o método **`generateSchedule`** para criar turnos:
    ```kotlin
    fun generateSchedule(availabilityList: List<Availability>): List<Schedule> {
        // Algoritmo para organizar turnos com base nas disponibilidades
    }
    ```
  - Os turnos criados são armazenados no Firebase:
    ```kotlin
    fun saveSchedule(schedule: Schedule) {
        val databaseRef = FirebaseDatabase.getInstance().getReference("schedules")
        databaseRef.push().setValue(schedule)
    }
    ```
  - A classe **`Schedule`** organiza as informações:
    ```kotlin
    data class Schedule(
        val date: String,
        val volunteers: List<String>
    )
    ```

### 7. **Relatórios**
- O **`ReportScreen.kt`** apresenta relatórios detalhados, gerados pelo **`ReportRepositoryImpl.kt`**, com base nos dados armazenados no Firebase.
