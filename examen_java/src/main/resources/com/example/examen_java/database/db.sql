-- 1. Création de la base de données
CREATE DATABASE IF NOT EXISTS RestaurantDB;
USE RestaurantDB;

-- 2. Création de la table Client
CREATE TABLE Client (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- 3. Création de la table Commande
CREATE TABLE Commande (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    client_id INT,
    FOREIGN KEY (client_id) REFERENCES Client(id) ON DELETE SET NULL
);

-- 4. Création de la table PlatPrincipal
CREATE TABLE PlatPrincipal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    description TEXT,
    prixDeBase DOUBLE NOT NULL
);

-- 5. Création de la table Ingredient
CREATE TABLE Ingredient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    quantite DOUBLE NOT NULL,
    unite VARCHAR(20)
);

-- 6. Création de la table Supplement
CREATE TABLE Supplement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prix DOUBLE NOT NULL
);

-- 7. Création de la table Repas
CREATE TABLE Repas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    platPrincipal_id INT,
    FOREIGN KEY (platPrincipal_id) REFERENCES PlatPrincipal(id) ON DELETE CASCADE
);

-- 8. Table d'association entre Repas et Ingredient (Relation Many-to-Many)
CREATE TABLE Repas_Ingredient (
    repas_id INT,
    ingredient_id INT,
    quantite DOUBLE NOT NULL,
    PRIMARY KEY (repas_id, ingredient_id),
    FOREIGN KEY (repas_id) REFERENCES Repas(id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id) ON DELETE CASCADE
);

-- 9. Table d'association entre Repas et Supplement (Relation Many-to-Many)
CREATE TABLE Repas_Supplement (
    repas_id INT,
    supplement_id INT,
    PRIMARY KEY (repas_id, supplement_id),
    FOREIGN KEY (repas_id) REFERENCES Repas(id) ON DELETE CASCADE,
    FOREIGN KEY (supplement_id) REFERENCES Supplement(id) ON DELETE CASCADE
);

-- 10. Table d'association entre Commande et Repas (Relation One-to-Many)
CREATE TABLE Commande_Repas (
    commande_id INT,
    repas_id INT,
    PRIMARY KEY (commande_id, repas_id),
    FOREIGN KEY (commande_id) REFERENCES Commande(id) ON DELETE CASCADE,
    FOREIGN KEY (repas_id) REFERENCES Repas(id) ON DELETE CASCADE
);
