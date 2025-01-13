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
values
    ('admin', '{noop}1111!', 'admin', '01000000000', '', 1, 1, 'ACTIVE', 'ADMIN', 'BASIC'),
    ('user1', '{noop}1234!', 'user1', '01011112222', '', 1, 1, 'ACTIVE', 'USER', 'BASIC');

insert into `user_roles` (`user_user_id`, `roles`)
values
    (1, 'USER'),
    (1, 'ADMIN'),
    (2, 'USER');

insert into `genre_category` (`genre_category_name`)
values
    ('Fiction'),
    ('Non-Fiction'),
    ('Mystery')
;


insert into `goods_category` (`goods_category_name`)
values
    ('Electronics'),
    ('Books'),
    ('Clothing')
;

insert into `feeds` (
    `uuid`,
    `title`,
    `price`,
    `content`,
    `view_count`,
    `like_count`,
    `feed_status`,
    `feed_type`,
    `user_id`
)
values
    ('abc123', 'Smartphone for Sale', 500.00, 'Selling a slightly used smartphone.', 10, 2, 'ON_SALE_OR_BUY', 'SALE',2),
    ('def456', 'Mystery Book Bundle', 30.00, 'A collection of mystery books.', 5, 1, 'ON_SALE_OR_BUY', 'SALE',2),
    ('ghi789', 'Winter Jacket', 70.00, 'Brand new winter jacket.', 8, 3, 'ON_SALE_OR_BUY','BUY', 2)
;

insert into `feed_images` (
    `file_name`,
    `file_url`,
    `image_index`,
    `feed_id`,
    `is_main`
)
values
    ('smartphone.jpg', 'http://example.com/images/smartphone.jpg', 0, 1, 1),
    ('book_bundle.jpg', 'http://example.com/images/book_bundle.jpg', 0, 2, 1),
    ('jacket.jpg', 'http://example.com/images/jacket.jpg', 0, 3, 1)
;

insert into `feed_genre_category` (`feed_id`, `genre_category_id`)
values
    (2, 1), -- Mystery Book Bundle -> Fiction
    (2, 3); -- Mystery Book Bundle ->
insert into `feed_goods_category` (`feed_id`, `goods_category_id`)
values
    (1, 1), -- Smartphone -> Electronics
    (3, 3); -- Winter Jacket -> Clothing