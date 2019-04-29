import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlantMallSharedModule } from 'app/shared';
import {
    SysAppointmentComponent,
    SysAppointmentDetailComponent,
    SysAppointmentUpdateComponent,
    SysAppointmentDeletePopupComponent,
    SysAppointmentDeleteDialogComponent,
    sysAppointmentRoute,
    sysAppointmentPopupRoute
} from './';

const ENTITY_STATES = [...sysAppointmentRoute, ...sysAppointmentPopupRoute];

@NgModule({
    imports: [PlantMallSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SysAppointmentComponent,
        SysAppointmentDetailComponent,
        SysAppointmentUpdateComponent,
        SysAppointmentDeleteDialogComponent,
        SysAppointmentDeletePopupComponent
    ],
    entryComponents: [
        SysAppointmentComponent,
        SysAppointmentUpdateComponent,
        SysAppointmentDeleteDialogComponent,
        SysAppointmentDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlantMallSysAppointmentModule {}
