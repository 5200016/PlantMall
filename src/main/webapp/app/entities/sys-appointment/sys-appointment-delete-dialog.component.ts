import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysAppointment } from 'app/shared/model/sys-appointment.model';
import { SysAppointmentService } from './sys-appointment.service';

@Component({
    selector: 'jhi-sys-appointment-delete-dialog',
    templateUrl: './sys-appointment-delete-dialog.component.html'
})
export class SysAppointmentDeleteDialogComponent {
    sysAppointment: ISysAppointment;

    constructor(
        protected sysAppointmentService: SysAppointmentService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysAppointmentService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysAppointmentListModification',
                content: 'Deleted an sysAppointment'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-appointment-delete-popup',
    template: ''
})
export class SysAppointmentDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysAppointment }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysAppointmentDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysAppointment = sysAppointment;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
