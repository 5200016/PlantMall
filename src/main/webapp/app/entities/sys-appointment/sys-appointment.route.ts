import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysAppointment } from 'app/shared/model/sys-appointment.model';
import { SysAppointmentService } from './sys-appointment.service';
import { SysAppointmentComponent } from './sys-appointment.component';
import { SysAppointmentDetailComponent } from './sys-appointment-detail.component';
import { SysAppointmentUpdateComponent } from './sys-appointment-update.component';
import { SysAppointmentDeletePopupComponent } from './sys-appointment-delete-dialog.component';
import { ISysAppointment } from 'app/shared/model/sys-appointment.model';

@Injectable({ providedIn: 'root' })
export class SysAppointmentResolve implements Resolve<ISysAppointment> {
    constructor(private service: SysAppointmentService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysAppointment> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysAppointment>) => response.ok),
                map((sysAppointment: HttpResponse<SysAppointment>) => sysAppointment.body)
            );
        }
        return of(new SysAppointment());
    }
}

export const sysAppointmentRoute: Routes = [
    {
        path: 'sys-appointment',
        component: SysAppointmentComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAppointment.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-appointment/:id/view',
        component: SysAppointmentDetailComponent,
        resolve: {
            sysAppointment: SysAppointmentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAppointment.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-appointment/new',
        component: SysAppointmentUpdateComponent,
        resolve: {
            sysAppointment: SysAppointmentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAppointment.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-appointment/:id/edit',
        component: SysAppointmentUpdateComponent,
        resolve: {
            sysAppointment: SysAppointmentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAppointment.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysAppointmentPopupRoute: Routes = [
    {
        path: 'sys-appointment/:id/delete',
        component: SysAppointmentDeletePopupComponent,
        resolve: {
            sysAppointment: SysAppointmentResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysAppointment.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
