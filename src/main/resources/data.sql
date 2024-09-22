insert into `users` (
    `username`,
    `password`,
    `nickname`,
    `phone_number`,
    `profile_image_url`,
    `email_verified`,
    `phone_verified`,
    `user_status`,
    `authority`,
    `login_type`


)

values ('admin','{noop}1111!','admin','01000000000','',
        1,1,'ACTIVE','ADMIN','BASIC'
       )
;

insert into `user_roles`(`user_user_id`, `roles`)
values (1, 'USER'),
       (1, 'ADMIN')
;