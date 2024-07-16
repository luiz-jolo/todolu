alter table taskcards add active tinyint;
update taskcards set active = 1;
