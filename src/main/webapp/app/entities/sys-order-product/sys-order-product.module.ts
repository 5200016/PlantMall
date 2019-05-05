import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysOrderProductComponent,
    SysOrderProductDetailComponent,
    SysOrderProductUpdateComponent,
    SysOrderProductDeletePopupComponent,
    SysOrderProductDeleteDialogComponent,
    sysOrderProductRoute,
    sysOrderProductPopupRoute
} from './';

const ENTITY_STATES = [...sysOrderProductRoute, ...sysOrderProductPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysOrderProductComponent,
        SysOrderProductDetailComponent,
        SysOrderProductUpdateComponent,
        SysOrderProductDeleteDialogComponent,
        SysOrderProductDeletePopupComponent
    ],
    entryComponents: [
        SysOrderProductComponent,
        SysOrderProductUpdateComponent,
        SysOrderProductDeleteDialogComponent,
        SysOrderProductDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysOrderProductModule {}
