import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysProductComponent,
    SysProductDetailComponent,
    SysProductUpdateComponent,
    SysProductDeletePopupComponent,
    SysProductDeleteDialogComponent,
    sysProductRoute,
    sysProductPopupRoute
} from './';

const ENTITY_STATES = [...sysProductRoute, ...sysProductPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysProductComponent,
        SysProductDetailComponent,
        SysProductUpdateComponent,
        SysProductDeleteDialogComponent,
        SysProductDeletePopupComponent
    ],
    entryComponents: [SysProductComponent, SysProductUpdateComponent, SysProductDeleteDialogComponent, SysProductDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysProductModule {}
