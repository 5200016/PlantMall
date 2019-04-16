import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysAdminComponent,
    SysAdminDetailComponent,
    SysAdminUpdateComponent,
    SysAdminDeletePopupComponent,
    SysAdminDeleteDialogComponent,
    sysAdminRoute,
    sysAdminPopupRoute
} from './';

const ENTITY_STATES = [...sysAdminRoute, ...sysAdminPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysAdminComponent,
        SysAdminDetailComponent,
        SysAdminUpdateComponent,
        SysAdminDeleteDialogComponent,
        SysAdminDeletePopupComponent
    ],
    entryComponents: [SysAdminComponent, SysAdminUpdateComponent, SysAdminDeleteDialogComponent, SysAdminDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysAdminModule {}
