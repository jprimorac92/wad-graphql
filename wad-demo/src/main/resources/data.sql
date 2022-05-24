insert into COURSE(title, description)
values ('English', 'English course');
insert into COURSE(title, description)
values ('Design patterns', 'Learn programming design patterns');
insert into COURSE(title, description)
values ('Math', 'Learn the science behind numbers');
insert into COURSE(title, description)
values ('Web Development', 'All about HTML, CSS and JS');

insert into PROFESSOR (name)
values ('John Smith');
insert into PROFESSOR (name)
values ('Luke Skywalker');
insert into PROFESSOR (name)
values ('Han Solo');
insert into PROFESSOR (name)
values ('Kylo Ren');

insert into STUDENT (name, level)
values ('Anakin Skywalker', 5);
insert into STUDENT (name, level)
values ('Princess Leia', 4);
insert into STUDENT (name, level)
values ('Boba Fett', 2);
insert into STUDENT (name, level)
values ('C-3PO', 2);

insert into professor_course
values (1, 1);
insert into professor_course
values (1, 2);
insert into professor_course
values (2, 3);

insert into student_course
values (1, 1);
insert into student_course
values (1, 2);
