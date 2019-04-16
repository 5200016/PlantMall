/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { PlantMallTestModule } from '../../../test.module';
import { SysShoppingCarDeleteDialogComponent } from 'app/entities/sys-shopping-car/sys-shopping-car-delete-dialog.component';
import { SysShoppingCarService } from 'app/entities/sys-shopping-car/sys-shopping-car.service';

describe('Component Tests', () => {
    describe('SysShoppingCar Management Delete Component', () => {
        let comp: SysShoppingCarDeleteDialogComponent;
        let fixture: ComponentFixture<SysShoppingCarDeleteDialogComponent>;
        let service: SysShoppingCarService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysShoppingCarDeleteDialogComponent]
            })
                .overrideTemplate(SysShoppingCarDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysShoppingCarDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysShoppingCarService);
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
