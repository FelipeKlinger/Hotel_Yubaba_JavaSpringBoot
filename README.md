# Aplicació Web per a la Gestió d'Hotels

## Descripció
Aquest projecte consisteix en el desenvolupament d'una aplicació web per gestionar la informació d'un hotel. L'aplicació permet gestionar clients, empleats, habitacions, reserves, factures i tasques realitzades a les habitacions.

## Requeriments Funcionals

### Categoria
Les categories d'hotel, com per exemple motel, 3 estrelles, etc.

- **id**: Identificador de la categoria
- **descripció**: Text que descriu la categoria

### Hotel
Informació relacionada amb els hotels gestionats.

- **id**: Identificador únic de l'hotel
- **nom**: Nom de l'hotel
- **adreça**: Ubicació de l'hotel
- **ciutat**: Ciutat on es troba l'hotel
- **categoria**: Categoria de l'hotel

### Habitació
Cada hotel disposa de diverses habitacions.

- **id**: Identificador únic de l'habitació
- **numero**: Número de l'habitació
- **tipus**: Tipus d'habitació (individual, doble, suite, etc.)
- **capacitat**: Nombre màxim de persones
- **preu_nit_AD**: Preu per nit amb allotjament i esmorzar
- **preu_nit_MP**: Preu per nit amb mitja pensió
- **estat**: Estat de l'habitació (disponible, ocupada, en neteja)
- **descripcio**: Informació addicional

### Persona
Les persones poden ser clients o empleats.

- **id**: Identificador únic
- **nom**: Nom de la persona
- **cognom**: Cognoms
- **adreça**: Adreça física
- **document_identitat**: DNI, passaport, etc.
- **data_naixement**: Data de naixement
- **telefon**: Telèfon
- **email**: Correu electrònic

### Client
Els clients poden fer reserves d'habitacions.

- **id**: Identificador únic
- **data_registre**: Data d'alta
- **tipus**: Categoria del client (Regular, VIP, etc.)
- **targeta_credit**: Número de targeta de crèdit

### Empleat
Els empleats treballen en diferents tasques a l'hotel.

- **id**: Identificador únic
- **llocFeina**: Posició dins de l'hotel
- **data_contratacio**: Data d'inici del contracte
- **salariBrut**: Salari de l'empleat
- **estat**: Estat laboral (actiu, baixa, permís)

### Reserva
Un client pot realitzar una o diverses reserves.

- **id**: Identificador únic
- **data_reserva**: Data de realització de la reserva
- **data_inici**: Data d'inici de la reserva
- **data_fi**: Data de finalització
- **tipus_reserva**: Tipus de reserva (AD, MP)
- **Tipus_IVA**: Percentatge d'IVA aplicat
- **preu_total_reserva**: Cost total de la reserva

### Tasca
Les tasques realitzades pels empleats.

- **id**: Identificador únic
- **descripcio**: Descripció de la tasca
- **data_creacio**: Data de creació
- **data_execucio**: Data prevista o efectiva d'execució
- **estat**: Estat de la tasca (pendent, en progrés, completada)

### Factura
Cada reserva genera una factura.

- **id**: Identificador únic
- **data_emisio**: Data d'emissió
- **metode_pagament**: Mètode de pagament
- **base_imposable**: Import abans d'IVA
- **iva**: Valor de l'IVA aplicat
- **total**: Total de la factura

## Requeriments Tècnics

- **Llenguatge**: Java
- **Framework**: Spring Boot
- **Interfície**: Thymeleaf
- **Arquitectura**: MVC (Model-Vista-Controlador)
- **Gestor de bases de dades**: MySQL amb JPA
- **Seguretat**: Spring Security
- **Diagrama UML** per planificació
- **Documentació**: Javadoc
- **Control de versions**: GitHub



