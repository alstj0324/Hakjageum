set global log_bin_trust_function_creators = 1;

drop function if exists replace_nickname;

create function `replace_nickname` (
    user_id varchar(100)
) returns varchar(100)
begin
    declare user_nickname varchar(100) default user_id;
    select nickname from users where id = user_id into user_nickname;

    return user_nickname;
end;

drop function if exists replace_uniquecode;

create function `replace_uniquecode` (
    unique_code varchar(100)
) returns varchar(100)
begin
    declare replace_code varchar(100) default unique_code;
    CASE unique_code
        WHEN 'BA0' THEN set replace_code = '자유게시판';
        WHEN 'BA1' THEN set replace_code = '취미게시판';
        WHEN 'BA2' THEN set replace_code = '도서게시판';
        WHEN 'HA0' THEN set replace_code = '독서';
        WHEN 'HA1' THEN set replace_code = '여행';
        WHEN 'HA2' THEN set replace_code = '운동';
        WHEN null THEN set replace_code = '식별불가';
        ELSE set replace_code = '알수없음';
        END CASE;

    return replace_code;
end;

drop procedure if exists get_post;

create procedure `get_post` (
    post_id int
)
begin
    select
        p.id as id,
        p.title as title,
        p.content as content,
        p.book_id as book_id,
        replace_uniquecode(p.hobby_code) as hobby_code,
        replace_uniquecode(p.board_code) as board_code,
        p.view_count as view_count,
        p.writer_id as writer_id,
        replace_nickname(p.writer_id) as nickname,
        p.created_at as created_at,
        p.updated_at as updated_at,
        p.deleted_at as deleted_at
    from postlist p
    where p.id = post_id;
end;

drop procedure if exists get_postlist;

create procedure `get_postlist` (
    b_code varchar(100)
)
begin
    select
        p.id as id,
        p.title as title,
        p.content as content,
        p.book_id as book_id,
        replace_uniquecode(p.hobby_code) as hobby_code,
        replace_uniquecode(p.board_code) as board_code,
        p.view_count as view_count,
        p.writer_id as writer_id,
        replace_nickname(p.writer_id) as nickname,
        p.created_at as created_at,
        p.updated_at as updated_at,
        p.deleted_at as deleted_at
    from postlist p
    where p.board_code = b_code and p.deleted_at is null
    order by p.id desc;
end;

drop procedure if exists get_postcount;

create procedure `get_postcount` (
    b_code varchar(100)
)
begin
    select
        count(*) as totalCount
    from postlist
    where board_code = b_code and deleted_at is null
    order by id desc;
end;

drop procedure if exists insert_post;

create procedure `insert_post` (
    p_title varchar(100),
    p_content varchar(1000),
    p_hobby_code varchar(20),
    p_board_code varchar(100),
    p_writer_id varchar(100),
    p_book_id varchar(20)
)
begin
    insert into postlist (
        title,
        content,
        book_id,
        hobby_code,
        board_code,
        writer_id
    ) values (
         p_title,
         p_content,
         p_book_id,
         p_hobby_code,
         p_board_code,
         p_writer_id
     );
end;

drop procedure if exists inc_viewcount;

create procedure `inc_viewcount` (
    p_id int
)
begin
    update postlist
    set view_count = view_count + 1
    where id = p_id;
end;

drop procedure if exists update_post;

create procedure `update_post` (
    p_title varchar(100),
    p_content varchar(1000),
    p_id int
)
begin
    update postlist
    set title = p_title,
        content = p_content
    where id = p_id;
end;

drop procedure if exists delete_post;

create procedure `delete_post` (
    p_id int
)
begin
    update postlist
    set deleted_at = now()
    where id = p_id;
end;

drop procedure if exists insert_comment;

create procedure `insert_comment` (
    p_id int,
    c_writer_id varchar(100),
    c_content varchar(1000)
)
begin
    insert into commentlist (
        post_id,
        writer_id,
        content
    ) values (
         p_id,
         c_writer_id,
         c_content
    );
end;

drop procedure if exists get_commentlist;

create procedure `get_commentlist` (
    p_id int
)
begin
    select
        c.post_id as post_id,
        c.writer_id as writer_id,
        replace_nickname(c.writer_id) as nickname,
        c.content as content,
        c.created_at as created_at,
        c.deleted_at as deleted_at,
        c.comment_id as comment_id
    from commentlist c
    where c.post_id = p_id and c.deleted_at is null
    order by c.comment_id desc;
end;

drop procedure if exists delete_comment;

create procedure `delete_comment` (
    c_id int
)
begin
    update commentlist
    set deleted_at = now()
    where comment_id = c_id;
end;

drop procedure if exists get_postlist_limit;

create procedure `get_postlist_limit`(
    b_code varchar(100),
    p_limit int,
    p_offset int
)
begin
    select
        p.id as id,
        p.title as title,
        p.content as content,
        p.book_id as book_id,
        replace_uniquecode(p.hobby_code) as hobby_code,
        replace_uniquecode(p.board_code) as board_code,
        p.view_count as view_count,
        p.writer_id as writer_id,
        replace_nickname(p.writer_id) as nickname,
        p.created_at as created_at,
        p.updated_at as updated_at,
        p.deleted_at as deleted_at
    from postlist p
    where p.board_code = b_code and p.deleted_at is null
    order by p.id desc
    limit p_limit offset p_offset;
end;