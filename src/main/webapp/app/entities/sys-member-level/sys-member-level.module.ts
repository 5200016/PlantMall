import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysMemberLevelComponent,
    SysMemberLevelDetailComponent,
    SysMemberLevelUpdateComponent,
    SysMemberLevelDeletePopupComponent,
    SysMemberLevelDeleteDialogComponent,
    sysMemberLevelRoute,
    sysMemberLevelPopupRoute
} from './';

const ENTITY_STATES = [...sysMemberLevelRoute, ...sysMemberLevelPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysMemberLevelComponent,
        SysMemberLevelDetailComponent,
        SysMemberLevelUpdateComponent,
        SysMemberLevelDeleteDialogComponent,
        SysMemberLevelDeletePopupComponent
    ],
    entryComponents: [
        SysMemberLevelComponent,
        SysMemberLevelUpdateComponent,
        SysMemberLevelDeleteDialogComponent,
        SysMemberLevelDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysMemberLevelModule {}
