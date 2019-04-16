import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysOrderComponent,
    SysOrderDetailComponent,
    SysOrderUpdateComponent,
    SysOrderDeletePopupComponent,
    SysOrderDeleteDialogComponent,
    sysOrderRoute,
    sysOrderPopupRoute
} from './';

const ENTITY_STATES = [...sysOrderRoute, ...sysOrderPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysOrderComponent,
        SysOrderDetailComponent,
        SysOrderUpdateComponent,
        SysOrderDeleteDialogComponent,
        SysOrderDeletePopupComponent
    ],
    entryComponents: [SysOrderComponent, SysOrderUpdateComponent, SysOrderDeleteDialogComponent, SysOrderDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysOrderModule {}
