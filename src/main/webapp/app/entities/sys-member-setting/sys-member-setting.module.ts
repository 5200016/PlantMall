import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysMemberSettingComponent,
    SysMemberSettingDetailComponent,
    SysMemberSettingUpdateComponent,
    SysMemberSettingDeletePopupComponent,
    SysMemberSettingDeleteDialogComponent,
    sysMemberSettingRoute,
    sysMemberSettingPopupRoute
} from './';

const ENTITY_STATES = [...sysMemberSettingRoute, ...sysMemberSettingPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysMemberSettingComponent,
        SysMemberSettingDetailComponent,
        SysMemberSettingUpdateComponent,
        SysMemberSettingDeleteDialogComponent,
        SysMemberSettingDeletePopupComponent
    ],
    entryComponents: [
        SysMemberSettingComponent,
        SysMemberSettingUpdateComponent,
        SysMemberSettingDeleteDialogComponent,
        SysMemberSettingDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysMemberSettingModule {}
