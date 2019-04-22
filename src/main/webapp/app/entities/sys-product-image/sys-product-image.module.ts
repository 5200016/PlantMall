import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysProductImageComponent,
    SysProductImageDetailComponent,
    SysProductImageUpdateComponent,
    SysProductImageDeletePopupComponent,
    SysProductImageDeleteDialogComponent,
    sysProductImageRoute,
    sysProductImagePopupRoute
} from './';

const ENTITY_STATES = [...sysProductImageRoute, ...sysProductImagePopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysProductImageComponent,
        SysProductImageDetailComponent,
        SysProductImageUpdateComponent,
        SysProductImageDeleteDialogComponent,
        SysProductImageDeletePopupComponent
    ],
    entryComponents: [
        SysProductImageComponent,
        SysProductImageUpdateComponent,
        SysProductImageDeleteDialogComponent,
        SysProductImageDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysProductImageModule {}
