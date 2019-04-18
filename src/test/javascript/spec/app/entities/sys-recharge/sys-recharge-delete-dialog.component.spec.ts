/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysRechargeDeleteDialogComponent } from 'app/entities/sys-recharge/sys-recharge-delete-dialog.component';
import { SysRechargeService } from 'app/entities/sys-recharge/sys-recharge.service';

describe('Component Tests', () => {
    describe('SysRecharge Management Delete Component', () => {
        let comp: SysRechargeDeleteDialogComponent;
        let fixture: ComponentFixture<SysRechargeDeleteDialogComponent>;
        let service: SysRechargeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysRechargeDeleteDialogComponent]
            })
                .overrideTemplate(SysRechargeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysRechargeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysRechargeService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
