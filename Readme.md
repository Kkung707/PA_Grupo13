# Apoio360 - Loja Social

## Autor
**Membros do Grupo:**
- Daniela Brito (N.º 25591)
- Joana Pimenta (N.º 22999)
- José Pinheiro (N.º 23129)
- Rodrigo Pilar (N.º 26536)

**Curso:** Licenciatura em Engenharia de Sistemas Informáticos, 3.º Ano  
**Unidade Curricular:** Projeto Aplicado  
**Docente:** Professora Patrícia Leite  
**Ano Letivo:** 2024/2025  

---

## Introdução
A aplicação **Apoio360** tem como objetivo modernizar a gestão de uma Loja Social que presta apoio a famílias em situação de vulnerabilidade. O projeto visa digitalizar e simplificar processos como o registo de beneficiários, gestão de doações e organização de turnos de voluntários, promovendo uma abordagem mais eficiente e intuitiva.

A aplicação será desenvolvida em **Kotlin**, utilizando o **Firebase** como backend para gestão de dados e infraestrutura. O foco estará na criação de uma interface amigável e acessível, com funcionalidades que atendam às necessidades de voluntários e gestores.

## Estrutura do Projeto
A organização do código seguirá o modelo de **Clean Architecture**, dividindo as responsabilidades em camadas distintas. Esta abordagem facilita a manutenção e a escalabilidade do projeto.

### Estrutura de Pastas e Ficheiros (Prevista)
```
app/
├── src/
│   ├── main/
│   │   └── java/com/example/apoio360/
│   │       ├── MainActivity.kt (Classe principal da aplicação)
│   │       ├── MyApplication.kt (Configuração inicial da aplicação)
│   │       │
│   │       ├── data/ 
│   │       │   ├── local/
│   │       │   │   ├── dao/
│   │       │   │   │   └── BeneficiaryDao.kt (Interface para operações na base de dados de beneficiários)
│   │       │   │   ├── database/
│   │       │   │   │   └── AppDatabase.kt (Configuração da base de dados Room)
│   │       │   │   └── entity/
│   │       │   │       ├── BeneficiaryEntity.kt (Modelo para beneficiar na base de dados)
│   │       │   │       ├── DonationEntity.kt (Modelo para doações na base de dados)
│   │       │   │       └── VolunteerEntity.kt (Modelo para voluntários na base de dados)
│   │       │   │
│   │       │   └── remote/
│   │       │       ├── api/
│   │       │       │   └── ApoioApi.kt (Interface Retrofit para integração com Firebase)
│   │       │       ├── model/
│   │       │       │   ├── BeneficiaryDto.kt (Modelo para beneficiários recebidos do Firebase)
│   │       │       │   ├── DonationDto.kt (Modelo para doações recebidas do Firebase)
│   │       │       │   └── VolunteerDto.kt (Modelo para voluntários recebidos do Firebase)
│   │       │       └── repository/
│   │       │           ├── BeneficiaryRepositoryImpl.kt (Repositório para gerir beneficiários)
│   │       │           └── DonationRepositoryImpl.kt (Repositório para gerir doações)
│   │       │
│   │       ├── di/
│   │       │   ├── NetworkModule.kt (Configuração de Retrofit e Firebase)
│   │       │   ├── RoomModule.kt (Configuração da base de dados Room)
│   │       │   └── RepositoryModule.kt (Configuração dos repositórios)
│   │       │
│   │       ├── domain/
│   │       │   ├── model/
│   │       │   │   ├── Beneficiary.kt (Modelo de domínio para beneficiários)
│   │       │   │   ├── Donation.kt (Modelo de domínio para doações)
│   │       │   │   └── Volunteer.kt (Modelo de domínio para voluntários)
│   │       │   │
│   │       │   ├── repository/
│   │       │   │   └── BeneficiaryRepository.kt (Interface para operações relacionadas com beneficiários)
│   │       │   │
│   │       │   └── use_case/
│   │       │       ├── AddBeneficiaryUseCase.kt (Caso de uso para adicionar beneficiários)
│   │       │       ├── GetDonationsUseCase.kt (Caso de uso para listar doações)
│   │       │       └── ManageShiftsUseCase.kt (Caso de uso para gerir turnos)
│   │       │
│   │       └── presentation/
│   │           ├── beneficiary/
│   │           │   ├── BeneficiaryScreen.kt (Ecrã para gestão de beneficiários)
│   │           │   └── BeneficiaryViewModel.kt (ViewModel para beneficiários)
│   │           │
│   │           ├── donation/
│   │           │   ├── DonationScreen.kt (Ecrã para gestão de doações)
│   │           │   └── DonationViewModel.kt (ViewModel para doações)
│   │           │
│   │           ├── volunteer/
│   │           │   ├── ShiftScreen.kt (Ecrã para gestão de turnos de voluntários)
│   │           │   └── ShiftViewModel.kt (ViewModel para turnos)
│   │           │
│   │           └── ui/
│   │               ├── screen/
│   │               │   └── MainScreen.kt (Ecrã principal para organizar a navegação)
│   │               └── theme/
│   │                   └── ApoioTheme.kt (Configuração do tema visual da aplicação)
│   │
│   └── test/
│       ├── BeneficiaryDaoTest.kt (Testes para a base de dados de beneficiários)
│       ├── DonationDaoTest.kt (Testes para a base de dados de doações)
│       └── ShiftManagementTest.kt (Testes para a gestão de turnos)
│
└── build.gradle.kts (Configuração do Gradle para o projeto)
```

## Próximos Passos
1. Criar os ficheiros base para a estrutura acima.
2. Configurar a integração com o Firebase (autenticação e base de dados).
3. Implementar os primeiros casos de uso: adicionar beneficiários, registar doações e organizar turnos de voluntários.
4. Criar mockups e protótipos para a interface usando o Figma.
5. Testar todas as funcionalidades implementadas para garantir estabilidade e usabilidade.

---

