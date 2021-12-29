create table new_schema.musique
(
    id                    bigint       not null
        primary key,
    nom_groupe_ou_artiste varchar(50)  not null,
    annee_sortie          int          not null,
    titre                 varchar(150) null
);

create table new_schema.bo
(
    id         bigint       not null
        primary key,
    musique_id bigint       not null,
    titre_film varchar(150) null,
    constraint bo_musique_id_fk
        foreign key (musique_id) references new_schema.musique (id)
);

create table new_schema.live
(
    id           bigint      not null
        primary key,
    lieu_concert varchar(50) not null,
    musique_id   bigint      null,
    constraint live_musique_id_fk
        foreign key (musique_id) references new_schema.musique (id)
);