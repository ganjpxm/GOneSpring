ALTER TABLE am_user ADD UNIQUE `user_cd_lang_index`(`user_cd`, `lang`);
ALTER TABLE am_user ADD UNIQUE `mobile_number_lang_index`(`mobile_number`, `lang`);
ALTER TABLE am_user ADD UNIQUE `email_lang_index`(`email`, `lang`);
ALTER TABLE am_role ADD UNIQUE `role_cd_lang_index`(`role_cd`, `lang`);
ALTER TABLE am_org ADD UNIQUE  `org_cd_lang_index`(`org_cd`, `lang`);
ALTER TABLE am_menu ADD UNIQUE `menu_cd_lang_index`(`menu_cd`, `lang`);
ALTER TABLE am_subsystem ADD UNIQUE `subsystem_cd_lang_index`(`subsystem_cd`, `lang`);
ALTER TABLE bm_config ADD UNIQUE `config_cd_lang_index`(`config_cd`, `lang`);
ALTER TABLE bm_param ADD UNIQUE `config_cd_lang_index`(`param_cd`, `lang`);
ALTER TABLE cm_image ADD UNIQUE `image_name_index`(`image_name`, `lang`);

ALTER TABLE am_role_subsystem ADD UNIQUE `subsystem_id_role_id_index`(`subsystem_id`, `role_id`);
ALTER TABLE am_user_role ADD UNIQUE `user_id_role_id_index`(`user_id`, `role_id`);

alter table am_role_menu add constraint FK_me_relate_ro_me foreign key (menu_id)
      references am_menu (menu_id) on delete restrict on update restrict;

alter table am_role_menu add constraint FK_ro_relate_ro_me foreign key (role_id)
      references am_role (role_id) on delete restrict on update restrict;

alter table am_role_subsystem add constraint FK_ro_relate_ro_su foreign key (role_id)
      references am_role (role_id) on delete restrict on update restrict;

alter table am_role_subsystem add constraint FK_su_relate_ro_su foreign key (subsystem_id)
      references am_subsystem (subsystem_id) on delete restrict on update restrict;

alter table am_subsystem_menu add constraint FK_me_relate_su_me foreign key (menu_id)
      references am_menu (menu_id) on delete restrict on update restrict;

alter table am_subsystem_menu add constraint FK_su_relate_su_me foreign key (subsystem_id)
      references am_subsystem (subsystem_id) on delete restrict on update restrict;

alter table am_user_org add constraint FK_or_relate_us_or foreign key (org_id)
      references am_org (org_id) on delete restrict on update restrict;

alter table am_user_org add constraint FK_us_relate_us_or foreign key (user_id)
      references am_user (user_id) on delete restrict on update restrict;

alter table am_user_role add constraint FK_ro_relate_us_ro foreign key (role_id)
      references am_role (role_id) on delete restrict on update restrict;

alter table am_user_role add constraint FK_us_relate_us_ro foreign key (user_id)
      references am_user (user_id) on delete restrict on update restrict;

alter table am_user_subsystem add constraint FK_su_relate_us_su foreign key (subsystem_id)
      references am_subsystem (subsystem_id) on delete restrict on update restrict;

alter table am_user_subsystem add constraint FK_us_relate_us_su foreign key (user_id)
      references am_user (user_id) on delete restrict on update restrict;

alter table bm_config add constraint FK_co_relate_su foreign key (subsystem_id)
      references am_subsystem (subsystem_id) on delete restrict on update restrict;

alter table bm_param add constraint FK_pa_relate_su foreign key (subsystem_id)
      references am_subsystem (subsystem_id) on delete restrict on update restrict;