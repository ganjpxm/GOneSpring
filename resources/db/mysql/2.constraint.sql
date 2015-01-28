ALTER TABLE am_user ADD UNIQUE `user_cd_lang_index`(`user_cd`, `lang`);
ALTER TABLE am_user ADD UNIQUE `mobile_number_lang_index`(`mobile_number`, `lang`);
ALTER TABLE am_user ADD UNIQUE `email_lang_index`(`email`, `lang`);
ALTER TABLE am_role ADD UNIQUE `role_cd_lang_index`(`role_cd`, `lang`);

alter table am_user_role add constraint FK_ro_relate_us_ro foreign key (role_id)
      references am_role (role_id) on delete restrict on update restrict;
alter table am_user_role add constraint FK_us_relate_us_ro foreign key (user_id)
      references am_user (user_id) on delete restrict on update restrict;